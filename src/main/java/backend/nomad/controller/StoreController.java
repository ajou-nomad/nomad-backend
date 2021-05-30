package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.dto.group.GroupOrderResponseDto;
import backend.nomad.dto.member.MemberOrderResponseDto;
import backend.nomad.dto.member.MemberResponseDto;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreRequestDto;
import backend.nomad.dto.store.StoreResponseDto;
import backend.nomad.service.DeliveryGroupService;
import backend.nomad.service.MemberService;
import backend.nomad.service.MenuService;
import backend.nomad.service.StoreService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final MemberService memberService;
    private final MenuService menuService;
    private final DeliveryGroupService deliveryGroupService;



    // 매장 생성
    @PostMapping("/store")
    public void saveStore(@RequestBody StoreRequestDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();



        Member member = memberService.findByUid(uid);
        Store store = new Store();
        store.setMember(member);

//        member.getStore().setMember(member);
        store.setStoreName(dto.getStoreName());
        store.setPhoneNumber(dto.getPhoneNumber());
        store.setAddress(dto.getAddress());
        store.setLatitude(dto.getLatitude());
        store.setLongitude(dto.getLongitude());
        store.setOpenTime(dto.getOpenTime());
        store.setCloseTime(dto.getCloseTime());
        store.setDeliveryTip(dto.getDeliveryTip());
        store.setLogoUrl(dto.getLogoUrl());
        store.setNotice(dto.getNotice());
        store.setStoreIntro(dto.getStoreIntro());
        store.setCategory(dto.getCategory());

        storeService.save(store);
        memberService.save(store.getMember());
    }

    // 배달 그룹에서 모든 매장 조회
    @GetMapping("/storeList")
    public Result getAllStoreList() {
        List<Store> store = storeService.findStores();
        List<StoreResponseDto> dtoList = new ArrayList<>();
        for (Store x : store) {
            List<Menu> menu = x.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription(), m.getImgUrl()))
                    .collect(Collectors.toList());

            List<Review> review = x.getReview();
            List<ReviewResponseDto> reviewList = review.stream()
                    .map(m -> new ReviewResponseDto(m.getReviewId(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                    .collect(Collectors.toList());

            StoreResponseDto dto = new StoreResponseDto(x.getStoreId(), x.getStoreName(), x.getPhoneNumber(), x.getAddress(), x.getLatitude(), x.getLongitude(), x.getOpenTime(), x.getCloseTime(), x.getDeliveryTip(), x.getLogoUrl(), menuList, reviewList, x.getRate(), x.getNotice(), x.getStoreIntro(), x.getCategory());
            dtoList.add(dto);
        }
        return new Result(dtoList);
    }

    // 관리하는 매장리스트
    @GetMapping("/myStoreList")
    public Result getStoreList(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();

        List<Menu> menu = store.getMenu();
        List<MenuResponseDto> menuList = menu.stream()
                .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription()))
                .collect(Collectors.toList());

        List<Review> review = store.getReview();
        List<ReviewResponseDto> reviewList = review.stream()
                .map(m -> new ReviewResponseDto(m.getReviewId(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                .collect(Collectors.toList());

        StoreResponseDto storeResponseDto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList, reviewList, store.getRate(), store.getNotice(), store.getStoreIntro(), store.getCategory());
        return new Result(storeResponseDto);
//        return new Result(storeResponseDto);
    }

    //모집 완료된 주문 불러오기 및 접수
    @GetMapping("/groupOrder")
    public Result orderList(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();

        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByOrderStatusAndStoreId(OrderStatus.recruitmentDone, store.getStoreId());
        List<MemberOrderResponseDto> orderItemList = new ArrayList<>();

        for (DeliveryGroup x : deliveryGroup) {
            List<MemberOrder> memberOrder = x.getMemberOrders();

            for (MemberOrder y : memberOrder) {
                MemberOrder orderItems = y;

                List<OrderItem> orders = orderItems.getOrderItem();
                List<OrderItemResponseDto> ordersDto = orders.stream()
                        .map(m -> new OrderItemResponseDto(m.getOrderItemId(), m.getMenuName(), m.getCost(), m.getQuantity()))
                        .collect(Collectors.toList());

                MemberOrderResponseDto orderItem = new MemberOrderResponseDto(orderItems.getMemberOrderId(), ordersDto);

                orderItemList.add(orderItem);
            }
        }
        List<DeliveryGroupResponseDto> collect = deliveryGroup.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuildingName(), m.getDeliveryDateTime(), m.getCurrent(), m.getMaxValue(), m.getGroupType(), m.getOrderStatus(), orderItemList))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("/groupOrder")
    public void orderConfirm(@RequestHeader("Authorization") String header, @RequestBody DeliveryGroupRequestDto deliveryGroupRequestDto) throws FirebaseAuthException {

        DeliveryGroup deliveryGroup = deliveryGroupService.findById(deliveryGroupRequestDto.getGroupId());
        deliveryGroup.setOrderStatus(OrderStatus.waitingForDelivery);

        deliveryGroupService.save(deliveryGroup);

    }



    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class ResultList<T, U> {
        private T storeData;
        private U menuList;
    }
}
