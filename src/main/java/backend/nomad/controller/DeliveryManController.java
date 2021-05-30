package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.member.Chat;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.dto.group.GroupOrderRequestDto;
import backend.nomad.dto.member.MemberOrderResponseDto;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import backend.nomad.dto.store.DeliveryGroupDto;
import backend.nomad.dto.store.OrderItemDto;
import backend.nomad.service.ChatService;
import backend.nomad.service.DeliveryGroupService;
import backend.nomad.service.MemberService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DeliveryManController {

    private final DeliveryGroupService deliveryGroupService;
    private final MemberService memberService;
    private final ChatService chatService;

    @GetMapping("/delivery")
    public Result getDeliveryGroupData() {
        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByOrderStatus(OrderStatus.waitingForDelivery);

        List<DeliveryGroupResponseDto> collect = deliveryGroup.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuildingName(), m.getDeliveryDateTime(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("/delivery")
    public Result setDeliveryGroupData(@RequestBody GroupOrderRequestDto groupOrderRequestDto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);

        DeliveryGroup deliveryGroup = deliveryGroupService.findById(groupOrderRequestDto.getGroupId());
        member.setDeliveryGroup(deliveryGroup);
        member.changeGroup(deliveryGroup);
        memberService.save(member);

        Chat chat = new Chat();
        deliveryGroup.setChat(chat);
        chatService.save(chat);

        deliveryGroup.setOrderStatus(OrderStatus.delivering);
        deliveryGroupService.save(deliveryGroup);

        List<Member> memberList = deliveryGroup.getMemberList();

        List<String> uidList = new ArrayList<>();

        for (Member x : memberList) {
            uidList.add(x.getUid());
        }

        return new Result(uidList);
    }

    @PostMapping("/deliveryComplete")
    public void deliveryComplete(@RequestBody DeliveryGroupRequestDto deliveryGroupRequestDto) {
        DeliveryGroup deliveryGroup = deliveryGroupService.findById(deliveryGroupRequestDto.getGroupId());
        Chat chat = deliveryGroup.getChat();

        List<Member> memberList = deliveryGroup.getMemberList();

        for (Member m : memberList) {
            m.deleteGroup(deliveryGroup);
            m.setDeliveryGroup(null);
            memberService.save(m);

            chat.deleteMember(m);
        }

        chatService.delete(chat);

        deliveryGroup.setOrderStatus(OrderStatus.deliveryDone);
        deliveryGroupService.save(deliveryGroup);
    }

    @GetMapping("/deliveryComplete")
    public Result getDeliveryComplete(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();

        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByOrderStatusAndStoreId(OrderStatus.deliveryDone, store.getStoreId());

        List<DeliveryGroupDto> dtoList = new ArrayList<>();

        for (DeliveryGroup x : deliveryGroup) {
            List<List<OrderItemDto>> orderItemList = new ArrayList<>();

            List<MemberOrder> memberOrder = x.getMemberOrders();


            for (MemberOrder y : memberOrder) {

                List<OrderItem> orderItems = y.getOrderItem();
                List<OrderItemDto> ordersDto = orderItems.stream()
                        .map(m -> new OrderItemDto(m.getMenuName(), m.getCost(), m.getQuantity()))
                        .collect(Collectors.toList());

                orderItemList.add(ordersDto);
            }

            DeliveryGroupDto collect = new DeliveryGroupDto(x.getGroupId(), x.getStoreId(), x.getLatitude(), x.getLongitude(), x.getAddress(), x.getBuildingName(), x.getDeliveryDateTime(), x.getOrderStatus(), orderItemList);
            dtoList.add(collect);

        }

        return new Result(dtoList);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }
}
