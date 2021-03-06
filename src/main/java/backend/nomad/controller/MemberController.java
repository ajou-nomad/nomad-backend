package backend.nomad.controller;


import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.likestore.LikeStore;
import backend.nomad.domain.member.Chat;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberChat;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.chat.ChatDto;
import backend.nomad.dto.chat.ChatRequestDto;
import backend.nomad.dto.member.MemberResponseDto;
import backend.nomad.dto.member.MemberRequestDto;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreResponseDto;
import backend.nomad.service.ChatService;
import backend.nomad.service.DeliveryGroupService;
import backend.nomad.service.MemberChatService;
import backend.nomad.service.MemberService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final DeliveryGroupService deliveryGroupService;
    private final ChatService chatService;
    private final MemberChatService memberChatService;

    @GetMapping("/test")
    public Integer test() {
        return 1;
    }

    @PostMapping("/member")
    public Long saveMember(@RequestHeader("Authorization") String header, @RequestBody MemberRequestDto dto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = new Member();
        member.setEmail(dto.getEmail());
        member.setNickName(dto.getNickName());
        member.setPhoneNum(dto.getPhoneNum());
        member.setToken(dto.getToken());
        member.setUid(uid);
        member.setMemberType(dto.getMemberType());
        member.setShopIdNumber(dto.getShopIdNumber());
        member.setDeliIdNumber(dto.getDeliIdNumber());
        member.setPoint(0.0);

        memberService.save(member);

        return new Long(1);
    }

    @GetMapping("/member")
    public Result authUser(@RequestHeader("Authorization") String header) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        if (memberService.findByUid(uid) != null) {
            Member member = memberService.findByUid(uid);
            MemberResponseDto memberResponseDto = new MemberResponseDto( member.getMemberId(), member.getNickName(), member.getEmail(), member.getPhoneNum(), member.getToken(), member.getUid(), member.getMemberType(), member.getPoint(), member.getShopIdNumber(), member.getDeliIdNumber());

            Store store = member.getStore();

            if (store != null) {
                memberResponseDto.setStoreId(store.getStoreId());
            }

            return new Result(memberResponseDto);
        }

        else {
            return new Result(HttpStatus.SC_BAD_REQUEST);
        }
    }


    @GetMapping("/memberList")
    public Result findMembers() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberResponseDto> collect = findMembers.stream()
                .map(m -> new MemberResponseDto(m.getMemberId(), m.getNickName(), m.getEmail(), m.getPhoneNum(), m.getToken(), m.getUid(), m.getMemberType(), m.getPoint(), m.getShopIdNumber(), m.getDeliIdNumber()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("/chatId")
    public void setChat(@RequestBody ChatRequestDto chatRequestDto) {
        DeliveryGroup deliveryGroup = deliveryGroupService.findById(chatRequestDto.getGroupId());

        Chat chat = deliveryGroup.getChat();
        chat.setChatToken(chatRequestDto.getChatId());
        chatService.save(chat);

//        List<MemberChat> memberChat = chat.getMemberChat();

//        memberChat.setChat(chat);
//        memberChat.addMemberChatToChat(chat);
//        chatService.save(chat);
//        memberChatService.save(memberChat);

        List<Member> member = deliveryGroup.getMemberList();
        for (Member x : member) {
            MemberChat memberChat = new MemberChat();
            memberChat.setChat(chat);
            memberChat.addMemberChatToChat(chat);
            memberChat.setMember(x);
            memberChat.addMemberChatToMember(x);
            memberChatService.save(memberChat);
            chatService.save(chat);
            memberService.save(x);
        }
    }

    @GetMapping("/chatId")
    public Result chatList(@RequestHeader("Authorization") String header) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);

        List<MemberChat> memberChats = member.getMemberChat();

        List<ChatDto> dtoList = memberChats.stream()
                .map(m -> new ChatDto(m.getMember().getMemberId(), m.getChat().getChatToken() , m.getChat().getDeliveryGroup().getGroupId()))
                .collect(Collectors.toList());

        return new Result(dtoList);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class ResultList<T, U> {
        private T dataT;
        private U dataU;
    }
}
