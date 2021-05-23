package backend.nomad.controller;

import backend.nomad.domain.member.Member;
//import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.member.MemberRepository;
import backend.nomad.dto.member.MemberOrderRequestDto;
import backend.nomad.service.MemberOrderService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberOrderController {

    private final MemberOrderService memberOrderService;
    private final MemberRepository memberRepository;

//    @PostMapping("/memberOrder")
//    public Long saveMemberOrder(@RequestBody MemberOrderRequestDto memberOrderRequestDto) {
//        Long id = memberOrderRequestDto.getMember().getMemberId();
////        Member member = new Member();
//        member.setMemberId(id);
//        dto.setMember(member);
//        return memberOrderService.save(member);
//    }

    @GetMapping("/memberOrderList")
    public Result findMemberOrders(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberRepository.findByUid(uid);

        List<MemberOrder> memberOrder = member.getMemberOrder();

//        List<MemberOrder> findMemberOrders = memberOrderService.findMemberOrders();
//        List<MemberOrderMainResponseDto> collect = findMemberOrders.stream()
//                .map(m -> new MemberOrderMainResponseDto(m.getMemberOrderId(), m.getMember()))
//                .collect(Collectors.toList());

        return new Result(memberOrder);
    }
    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

}
