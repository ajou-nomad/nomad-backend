package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.member.MemberOrderCancelDto;
import backend.nomad.dto.member.MemberOrderResponseDto;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.service.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberOrderController {

    private final MemberOrderService memberOrderService;
    private final MemberService memberService;
    private final DeliveryGroupService deliveryGroupService;
    private final OrderItemService orderItemService;
    private final StoreService storeService;

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


            Review review = x.getReview();
            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            if (x.getReview() != null) {
                reviewResponseDto = new ReviewResponseDto(review.getReviewId(), null, null, review.getNickName(), review.getContents(), review.getImgUrl(), review.getRate(), review.getLocalDateTime());
            }

            if (x.getDeliveryGroup() != null) {
                MemberOrderResponseDto dto = new MemberOrderResponseDto(x.getMemberOrderId(), store.getStoreId(), x.getDeliveryGroup().getGroupId(), store.getStoreName(), x.getDeliveryGroup().getOrderStatus(), orderItemList, reviewResponseDto, x.getTotalCost(), x.getPayMethod(), x.getOrderTime());
                dtoList.add(dto);
            }
            else {
                MemberOrderResponseDto dto = new MemberOrderResponseDto(x.getMemberOrderId(), store.getStoreId(), null, store.getStoreName(), null, orderItemList, reviewResponseDto, x.getTotalCost(), x.getPayMethod(), x.getOrderTime());
                dtoList.add(dto);
            }
        }

        return new Result(dtoList);
    }

    @PostMapping("/memberOrderCancel")
    public Double cancel(@RequestHeader("Authorization") String header, @RequestBody MemberOrderCancelDto memberOrderCancelDto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        MemberOrder memberOrder = memberOrderService.findById(memberOrderCancelDto.getMemberOrderId());
        Member member = memberOrder.getMember();
        Store store = memberOrder.getStore();
        DeliveryGroup deliveryGroup = memberOrder.getDeliveryGroup();

        if (deliveryGroup.getMemberList().size() == 1) {
            member.deleteGroup(deliveryGroup);
            memberService.save(member);
            member.setDeliveryGroup(null);

            deliveryGroup.setOrderStatus(OrderStatus.cancel);
            deliveryGroupService.save(deliveryGroup);
        }
        else {
            member.deleteGroup(deliveryGroup);
            memberService.save(member);
            member.setDeliveryGroup(null);

            deliveryGroupService.save(deliveryGroup);
        }

        member.setPoint(member.getPoint() + memberOrder.getTotalCost());
        memberService.save(member);

//        List<OrderItem> orderItem = new CopyOnWriteArrayList<>();
//        orderItem.addAll(memberOrder.getOrderItem());

//        for (OrderItem x : orderItem) {
//
//            x.deleteMemberOrder(memberOrder);
//
//            orderItemService.save(x);
//        }

//        memberOrder.deleteMember(member);
//        memberService.save(member);

        memberOrder.deleteStore(store);
        storeService.save(store);
        memberOrder.setStore(null);

        memberOrder.deleteDeliveryGroup(deliveryGroup);
        deliveryGroupService.save(deliveryGroup);
        memberOrder.setDeliveryGroup(null);

        memberOrderService.save(memberOrder);

        return member.getPoint();
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

}
