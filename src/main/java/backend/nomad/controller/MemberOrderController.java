//package backend.nomad.controller;
//
//import backend.nomad.domain.member.Member;
////import backend.nomad.domain.member.MemberOrder;
//import backend.nomad.domain.member.MemberOrder;
//import backend.nomad.domain.member.MemberRepository;
//import backend.nomad.dto.member.MemberMainResponseDto;
//import backend.nomad.dto.member.MemberOrderMainResponseDto;
//import backend.nomad.dto.member.MemberOrderSaveRequestDto;
//import backend.nomad.dto.member.MemberSaveRequestDto;
//import backend.nomad.service.MemberOrderService;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequiredArgsConstructor
//public class MemberOrderController {
//
//    private final MemberOrderService memberOrderService;
//    private final MemberRepository memberRepository;
//
//    @PostMapping("/memberOrder")
//    public Long saveMemberOrder(@RequestBody MemberOrderSaveRequestDto dto) {
//        Long id = dto.getMember().getMemberId();
////        Member member = new Member();
//        member.setMemberId(id);
//        dto.setMember(member);
//        return memberOrderService.save(member);
//    }
//
//    @GetMapping("/memberOrderList")
//    public Result findMemberOrders() {
//        List<MemberOrder> findMemberOrders = memberOrderService.findMemberOrders();
//        List<MemberOrderMainResponseDto> collect = findMemberOrders.stream()
//                .map(m -> new MemberOrderMainResponseDto(m.getMemberOrderId(), m.getMember()))
//                .collect(Collectors.toList());
//
//        return new Result(collect);
//    }
//    @Data
//    @AllArgsConstructor
//    class Result<T> {
//        private T data;
//    }
//
//}
