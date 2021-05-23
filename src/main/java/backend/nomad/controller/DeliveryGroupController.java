package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.service.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        deliveryGroup.setOrderStatus("recruiting");

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


        Menu menu = menuService.findByMenuName(deliveryGroupRequestDto.getMenuName());

        int totalCost = menu.getCost() * deliveryGroupRequestDto.getQuantity();

        memberOrder.setTotalCost(totalCost);
        memberOrder.setPayMethod(deliveryGroupRequestDto.getPayMethod());
        memberOrder.setOrderTime(deliveryGroupRequestDto.getOrderTime());

        memberOrder.setMember(member);

        memberOrderService.save(memberOrder);
    }

    @PostMapping("/participationGroup")
    public Result addInGroup(@RequestBody DeliveryGroupRequestDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
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

        Menu menu = menuService.findByMenuName(dto.getMenuName());

        int cost = menu.getCost() * dto.getQuantity();

        memberOrder.setTotalCost(cost);
        memberOrder.setPayMethod(memberOrder.getPayMethod());
        memberOrder.setOrderTime(memberOrder.getOrderTime());
        memberOrder.setMember(member);

        memberOrderService.save(memberOrder);

        if (deliveryGroup.getCurrent() == deliveryGroup.getMaxValue()) {
            deliveryGroup.setOrderStatus("recruitmentDone");
            memberService.save(member);
            deliveryGroupService.save(deliveryGroup);
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
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuilding(), m.getTime(), m.getDate(),m.getCurrent(),  m.getMaxValue(),m.getGroupType(), m.getOrderStatus()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

//    @GetMapping("/daliyGroupData")
//    public Result findDaily() {
//
//    }
//
//    @GetMapping("/weeklyGroupData")
//    public Result findWeekly() {
//
//    }

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
