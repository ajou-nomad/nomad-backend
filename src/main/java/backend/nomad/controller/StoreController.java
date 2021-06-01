package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.member.Chat;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Promotion;
import backend.nomad.domain.store.PromotionMenu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.dto.group.GroupOrderRequestDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.dto.store.*;
import backend.nomad.service.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final MemberService memberService;
    private final MenuService menuService;
    private final DeliveryGroupService deliveryGroupService;
    private final ChatService chatService;



//    @Scheduled(fixedDelay = 120000)
//    public void manageChatRoom() {
//        List<DeliveryGroup> deliveryGroups = deliveryGroupService.findGroups();
//
//
//        log.info("배달 완료된 주문 탐색");
//        for (DeliveryGroup x : deliveryGroups) {
//            if (x.getDeliveryDateTime() != null && x.getOrderStatus().equals(OrderStatus.deliveryDone) && x.getDeliveryDateTime().getDayOfMonth() != (LocalDateTime.now().getDayOfMonth())) {
//                // 멀티쓰레드 이슈 로 CopyOnWriteArrayList 이용
//                Chat chat = x.getChat();
//
//                List<Member> memberList = new CopyOnWriteArrayList<>();
//
//                memberList.addAll(x.getMemberList());
//
//                for (Member y : memberList) {
//                    log.info("member: " + y);
//                    y.deleteGroup(x);
//                    y.setDeliveryGroup(null);
//
//                    deliveryGroupService.save(x);
//                    chat.deleteMember(y);
//
//                    memberService.save(y);
//
//                }
//                x.setChat(null);
//                chatService.delete(chat);
//
//            }
//        }
//    }

    // 매장 생성
    @PostMapping("/store")
    public void saveStore(@RequestBody StoreRequestDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();



        Member member = memberService.findByUid(uid);
        Store store = new Store();
        store.setMember(member);

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
//        store.setPromotion(dto.getPromotion());
        store.setPromotion(Promotion.Off);

        storeService.save(store);
        memberService.save(store.getMember());
    }

    // 배달 그룹에서 모든 매장 조회
    @GetMapping("/storeList")
    public Result getAllStoreList() {
        List<Store> store = storeService.findStores();
        List<StoreListDto> dtoList = new ArrayList<>();
        for (Store x : store) {
            List<Menu> menu = x.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription(), m.getImgUrl()))
                    .collect(Collectors.toList());

            List<Review> review = x.getReview();
            List<ReviewResponseDto> reviewList = review.stream()
                    .map(m -> new ReviewResponseDto(m.getReviewId(), m.getNickName(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                    .collect(Collectors.toList());

            PromotionMenu promotionMenu = x.getPromotionMenu();
            PromotionMenuDto promotionMenuDto = new PromotionMenuDto(promotionMenu.getPromotionMenuId(), promotionMenu.getPromotionMenuName(), promotionMenu.getCost(), promotionMenu.getDescription(), promotionMenu.getPromotionDescription(), promotionMenu.getImgUrl());

            StoreListDto dto = new StoreListDto(x.getStoreId(), x.getStoreName(), x.getPhoneNumber(), x.getAddress(), x.getLatitude(), x.getLongitude(), x.getOpenTime(), x.getCloseTime(), x.getDeliveryTip(), x.getLogoUrl(), menuList, reviewList, x.getRate(), x.getNotice(), x.getStoreIntro(), x.getCategory(),x.getPromotion(), promotionMenuDto);
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
                .map(m -> new ReviewResponseDto(m.getReviewId(), m.getNickName(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                .collect(Collectors.toList());

        StoreResponseDto storeResponseDto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList, reviewList, store.getRate(), store.getNotice(), store.getStoreIntro(), store.getCategory(), store.getPromotion());
        return new Result(storeResponseDto);
    }

    //모집 완료된 주문 불러오기 및 접수
    @GetMapping("/deliveryGroupOrder")
    public Result orderList(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();

        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByOrderStatusOrOrderStatusOrOrderStatusOrOrderStatusAndStoreId(OrderStatus.recruitmentDone, OrderStatus.recruitmentAccept, OrderStatus.delivering, OrderStatus.waitingForDelivery, store.getStoreId());

        List<DeliveryGroupDto> dtoList = new ArrayList<>();

        for (DeliveryGroup x : deliveryGroup) {
            if (x.getDeliveryDateTime().getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
                continue;
            }
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

    @PostMapping("/deliveryGroupOrder")
    public void orderConfirm(@RequestHeader("Authorization") String header, @RequestBody GroupOrderRequestDto groupOrderRequestDto) throws FirebaseAuthException {

        DeliveryGroup deliveryGroup = deliveryGroupService.findById(groupOrderRequestDto.getGroupId());
        deliveryGroup.setOrderStatus(groupOrderRequestDto.getOrderStatus());

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

//    @GetMapping("/sales")
//    public Result sales(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
//        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
//        String uid = decodedToken.getUid();
//
//        Member member = memberService.findByUid(uid);
//        Store store = member.getStore();
//
//        List<DeliveryGroup> deliveryGroups = deliveryGroupService.findByOrderStatus(OrderStatus.deliveryDone);
//
//        for (DeliveryGroup x : deliveryGroups) {
//            List<MemberOrder> memberOrders = x.getMemberOrders();
//            for (MemberOrder y : memberOrders) {
//                if (x.getDeliveryDateTime().getMonth() = )
//            }
//        }
//
//        for (int i = 1; i <= 12; i++) {
//            List<MemberOrder> memberOrders =
//            SalesDto salesDto = new SalesDto(i, )
//        }
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
        private T storeData;
        private U menuList;
    }
}
