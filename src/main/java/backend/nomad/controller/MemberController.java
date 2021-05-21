package backend.nomad.controller;


import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.member.Chat;
import backend.nomad.domain.member.Member;
import backend.nomad.dto.chat.ChatRequestDto;
import backend.nomad.dto.member.MemberResponseDto;
import backend.nomad.dto.member.MemberRequestDto;
import backend.nomad.service.ChatService;
import backend.nomad.service.DeliveryGroupService;
import backend.nomad.service.MemberService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final DeliveryGroupService deliveryGroupService;
    private final ChatService chatService;

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
        member.setPoint(dto.getPoint());

        memberService.save(member);

        return new Long(1);
    }

    @GetMapping("/member")
    public Result authUser(@RequestHeader("Authorization") String header) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();
        if (memberService.findByUid(uid) != null) {
            Member member = memberService.findByUid(uid);
            return new Result(member);
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

    @PostMapping("/ChatId")
    public void setChat(@RequestBody ChatRequestDto chatRequestDto) {
        DeliveryGroup deliveryGroup = deliveryGroupService.findById(chatRequestDto.getGroupId());

        Chat chat = new Chat();
        chat.setChatName(chatRequestDto.getChatId());

        chatService.save(chat);
        List<Member> member = deliveryGroup.getMemberList();
        for (Member x : member) {
            chat.addChat(x);
            memberService.save(x);
        }

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
