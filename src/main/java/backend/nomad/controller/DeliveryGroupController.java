package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.dto.store.MenuRequestDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreResponseDto;
import backend.nomad.firebase.FirebaseService;
import backend.nomad.service.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.messaging.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeliveryGroupController {

    private final DeliveryGroupService deliveryGroupService;
    private final MemberService memberService;
    private final StoreService storeService;
    private final MenuService menuService;
    private final MemberOrderService memberOrderService;
    private final FirebaseService firebaseService;
    private final OrderItemService orderItemService;

    @PostMapping("/groupData")
    public void SaveGroup(@RequestBody DeliveryGroupRequestDto deliveryGroupRequestDto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        DeliveryGroup deliveryGroup = new DeliveryGroup();
        deliveryGroup.setStoreId(deliveryGroupRequestDto.getStoreId());
        deliveryGroup.setLatitude(deliveryGroupRequestDto.getLatitude());
        deliveryGroup.setLongitude(deliveryGroupRequestDto.getLongitude());
        deliveryGroup.setAddress(deliveryGroupRequestDto.getAddress());
        deliveryGroup.setBuilding(deliveryGroupRequestDto.getBuilding());
        deliveryGroup.setTime(deliveryGroupRequestDto.getTime());
        deliveryGroup.setDate(deliveryGroupRequestDto.getDate());
        deliveryGroup.setCurrent(1);
        deliveryGroup.setMaxValue(deliveryGroupRequestDto.getMaxValue());
        deliveryGroup.setGroupType(deliveryGroupRequestDto.getGroupType());
        deliveryGroup.setOrderStatus(OrderStatus.recruiting);

        deliveryGroupService.save(deliveryGroup);

        Member member = memberService.findByUid(uid);
        member.setDeliveryGroup(deliveryGroup);
        member.changeGroup(deliveryGroup);
        memberService.save(member);

        //주문 데이터
        Store store = storeService.findByStoreId(deliveryGroupRequestDto.getStoreId());

        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setUid(uid);
        memberOrder.setStoreId(deliveryGroupRequestDto.getStoreId());

        memberOrder.setStore(store);


        List<MenuRequestDto> menuList = deliveryGroupRequestDto.getMenu();
        for (MenuRequestDto x : menuList) {
            Menu thisMenu = menuService.findByMenuName(x.getMenuName());

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuName(x.getMenuName());
            orderItem.setCost(x.getCost());
            orderItem.setQuantity(x.getQuantity());

            orderItem.addOrderItemToMemberOrder(memberOrder);

            memberOrderService.save(memberOrder);
            orderItemService.save(orderItem);

        }

        memberOrder.setPayMethod(deliveryGroupRequestDto.getPayMethod());
        memberOrder.setOrderTime(deliveryGroupRequestDto.getOrderTime());

        memberOrder.setMember(member);
//        Menu menu = menuService.findByMenuName(deliveryGroupRequestDto.getMenu().getMenuName());


//        int totalCost = menu.getCost() * deliveryGroupRequestDto.getQuantity();

//        memberOrder.setTotalCost(deliveryGroupRequestDto.);

        memberOrderService.save(memberOrder);
    }

    @PostMapping("/participationGroup")
    public Result addInGroup(@RequestBody DeliveryGroupRequestDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException, FirebaseMessagingException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        DeliveryGroup deliveryGroup = deliveryGroupService.findById(dto.getGroupId());
        member.setDeliveryGroup(deliveryGroup);
        member.changeGroup(deliveryGroup);

        //주문 데이터
        MemberOrder memberOrder = new MemberOrder();

        Store store = storeService.findByStoreId(dto.getStoreId());
        memberOrder.setStore(store);

        memberOrder.setUid(uid);
        memberOrder.setStoreId(dto.getStoreId());

//        Menu menu = menuService.findByMenuName(dto.getMenuName());

//        int cost = menu.getCost() * dto.getQuantity();

//        memberOrder.setTotalCost(dto.getCost());
        memberOrder.setPayMethod(memberOrder.getPayMethod());
        memberOrder.setOrderTime(memberOrder.getOrderTime());
        memberOrder.setMember(member);

        memberOrderService.save(memberOrder);

        if (deliveryGroup.getCurrent() == deliveryGroup.getMaxValue()) {
            deliveryGroup.setOrderStatus(OrderStatus.recruitmentDone);
            memberService.save(member);
            deliveryGroupService.save(deliveryGroup);

            List<Member> fcmMember = deliveryGroup.getMemberList();;

            for (Member x : fcmMember) {
                Message message = Message.builder()
                        .setNotification(Notification.builder()
                                .setTitle("모집이 완료됐습니다!")
                                .setBody("도착 시간: " + deliveryGroup.getBuilding() + "배달 시간: " + deliveryGroup.getTime())
                                .build())
                        // Device를 특정할 수 있는 토큰.
                        .setToken(x.getToken())
                        .build();

                FirebaseMessaging.getInstance().send(message);
            }


            return new Result("MAX");
        }

        memberService.save(member);
        deliveryGroupService.save(deliveryGroup);

        return new Result(HttpStatus.ACCEPTED);
    }

    @GetMapping("/groupList")
    public Result findGroups() {
        List<DeliveryGroup> findGroups = deliveryGroupService.findGroups();
        List<DeliveryGroupResponseDto> collect = findGroups.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuilding(), m.getTime(), m.getDate(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus()))
                .collect(Collectors.toList());

        return new Result(collect);
    }


    @GetMapping("/dailyGroupData")
    public ResultList findDaily() {

        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByGroupType(GroupType.day);

        List<DeliveryGroupResponseDto> collect = deliveryGroup.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuilding(), m.getTime(), m.getDate(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus()))
                .collect(Collectors.toList());

//        List<Store> store = storeService.findStores();
        List<StoreResponseDto> dtoList = new ArrayList<>();
        for (DeliveryGroup x : deliveryGroup) {
            Store store = storeService.findByStoreId(x.getStoreId());
            List<Menu> menu = store.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription()))
                    .collect(Collectors.toList());
            StoreResponseDto dto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList);
            dtoList.add(dto);
        }

        return new ResultList(collect, dtoList);
    }

    @GetMapping("/weeklyGroupData")
    public ResultList findWeekly() {
        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByGroupType(GroupType.weekly);

        List<DeliveryGroupResponseDto> collect = deliveryGroup.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuilding(), m.getTime(), m.getDate(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus()))
                .collect(Collectors.toList());

        List<StoreResponseDto> dtoList = new ArrayList<>();
        for (DeliveryGroup x : deliveryGroup) {
            Store store = storeService.findByStoreId(x.getStoreId());
            List<Menu> menu = store.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription()))
                    .collect(Collectors.toList());
            StoreResponseDto dto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList);
            dtoList.add(dto);
        }

        return new ResultList(collect, dtoList);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class ResultList<T, U> {
        private T dailyGroupData;
        private U storeData;
    }
}
