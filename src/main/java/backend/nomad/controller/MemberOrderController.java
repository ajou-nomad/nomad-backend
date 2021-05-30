package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.likestore.LikeStore;
import backend.nomad.domain.member.Member;
//import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.member.MemberRepository;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.dto.member.MemberOrderRequestDto;
import backend.nomad.dto.member.MemberOrderResponseDto;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreResponseDto;
import backend.nomad.service.DeliveryGroupService;
import backend.nomad.service.MemberOrderService;
import backend.nomad.service.MemberService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberOrderController {

    private final MemberOrderService memberOrderService;
    private final MemberService memberService;
    private final DeliveryGroupService deliveryGroupService;

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

        Member member = memberService.findByUid(uid);

        List<MemberOrderResponseDto> dtoList = new ArrayList<>();

        List<MemberOrder> memberOrder = member.getMemberOrder();

        for (MemberOrder x : memberOrder) {
            Store store = x.getStore();
            List<OrderItem> orderItems = x.getOrderItem();
            List<OrderItemResponseDto> orderItemList = orderItems.stream()
                    .map(m -> new OrderItemResponseDto(m.getOrderItemId(),m.getMenuName(), m.getCost(), m.getQuantity()))
                    .collect(Collectors.toList());

            List<Review> review = x.getMember().getReview();
            List<ReviewResponseDto> reviewList = review.stream()
                    .map(m -> new ReviewResponseDto(m.getReviewId(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                    .collect(Collectors.toList());
            if (x.getDeliveryGroup() != null) {
                MemberOrderResponseDto dto = new MemberOrderResponseDto(x.getMemberOrderId(), store.getStoreId(), store.getStoreName(), x.getDeliveryGroup().getOrderStatus(), orderItemList, reviewList, x.getTotalCost(), x.getPayMethod(), x.getOrderTime());
                dtoList.add(dto);
            }
        }

        return new Result(dtoList);
    }
    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

}
