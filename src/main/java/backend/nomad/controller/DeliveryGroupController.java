package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Promotion;
import backend.nomad.domain.store.PromotionMenu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.dto.store.MenuRequestDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreResponseDto;
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
public class DeliveryGroupController {

    private final DeliveryGroupService deliveryGroupService;
    private final MemberService memberService;
    private final StoreService storeService;
    private final MenuService menuService;
    private final MemberOrderService memberOrderService;
    private final OrderItemService orderItemService;

//    @Scheduled(fixedDelay = 120000)
//    public void manageGroupState() {
//        List<DeliveryGroup> deliveryGroups = deliveryGroupService.findGroups();
//
//        log.info("그룹주문 시간 탐색");
//        for (DeliveryGroup x : deliveryGroups) {
//            if (x.getDeliveryDateTime() != null && x.getOrderStatus().equals(OrderStatus.recruiting) && x.getDeliveryDateTime().isBefore(LocalDateTime.now())) {
//                // 멀티쓰레드 이슈 로 CopyOnWriteArrayList 이용
//                List<Member> memberList = new CopyOnWriteArrayList<>();
//
//                memberList.addAll(x.getMemberList());
//
//                for (Member y : memberList) {
//                    y.setDeliveryGroup(null);
//                    deliveryGroupService.save(x);
//                    memberService.save(y);
//                }
//
//                x.setOrderStatus(OrderStatus.cancel);
//                deliveryGroupService.save(x);
//
//                List<MemberOrder> memberOrders = new CopyOnWriteArrayList<>();
//
//                memberOrders.addAll(x.getMemberOrders());
//
//                for (MemberOrder z : memberOrders) {
//                    Member member = z.getMember();
//                    member.setPoint(z.getTotalCost());
//                }
//            }
//        }
//    }
//
//    @Scheduled(cron = "0 1 * * * *")
//    public void recommendOtherGroup() throws FirebaseMessagingException {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        List<DeliveryGroup> deliveryGroups = deliveryGroupService.findByDayAndOrderStatus(localDateTime.getDayOfMonth(), OrderStatus.recruiting);
//
//        log.info("추천 탐색");
//        for (DeliveryGroup x : deliveryGroups) {
//            Store storeX = storeService.findByStoreId(x.getStoreId());
//
//            if (x.getDeliveryDateTime().getHour() - localDateTime.getHour() == 1) {
//
//                for (DeliveryGroup y : deliveryGroups) {
//                    Store storeY = storeService.findByStoreId(y.getStoreId());
//
//                    double distance = distance(y.getLatitude(), y.getLongitude(), x.getLatitude(), x.getLongitude(), "meter");
//
//                    if (distance < 200 && y.getDeliveryDateTime().getHour() - x.getDeliveryDateTime().getHour() < 2 && y.getCurrent() > x.getCurrent() && storeX.getStoreName().equals(storeY.getStoreName()) || storeX.getCategory().equals(storeY.getCategory())) {
//
//                        List<Member> fcmMember = y.getMemberList();;
//
//                        for (Member member : fcmMember) {
//                            Message message = Message.builder()
//                                    .setNotification(Notification.builder()
//                                            .setTitle("배달 그룹 마감 시간 임박!!")
//                                            .setBody("이 배달 그룹은 어떠세요!?\n" + "매장: " + storeY.getStoreName() + "배달 장소: " + y.getBuildingName() + "\n" + "배달 시간: " + y.getDeliveryDateTime() + "\n" + "현재 모집인원이 " + (y.getMaxValue() - y.getCurrent()) + "명 남았습니다")
//                                            .build())
//                                    // Device를 특정할 수 있는 토큰.
//                                    .setToken(member.getToken())
//                                    .build();
//
//                            FirebaseMessaging.getInstance().send(message);
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
//
//        double theta = lon1 - lon2;
//        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
//
//        dist = Math.acos(dist);
//        dist = rad2deg(dist);
//        dist = dist * 60 * 1.1515;
//
//        if (unit.equals("kilometer")) {
//            dist = dist * 1.609344;
//        } else if(unit.equals("meter")){
//            dist = dist * 1609.344;
//        }
//
//        return (dist);
//    }
//
//    // This function converts decimal degrees to radians
//    private static double deg2rad(double deg) {
//        return (deg * Math.PI / 180.0);
//    }
//
//    // This function converts radians to decimal degrees
//    private static double rad2deg(double rad) {
//        return (rad * 180 / Math.PI);
//    }


    @PostMapping("/deliveryGroup")
    public void SaveGroup(@RequestBody DeliveryGroupRequestDto deliveryGroupRequestDto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        DeliveryGroup deliveryGroup = new DeliveryGroup();
        deliveryGroup.setStoreId(deliveryGroupRequestDto.getStoreId());
        deliveryGroup.setLatitude(deliveryGroupRequestDto.getLatitude());
        deliveryGroup.setLongitude(deliveryGroupRequestDto.getLongitude());
        deliveryGroup.setAddress(deliveryGroupRequestDto.getAddress());
        deliveryGroup.setBuildingName(deliveryGroupRequestDto.getBuildingName());
        deliveryGroup.setCurrent(1);
        deliveryGroup.setMaxValue(deliveryGroupRequestDto.getMaxValue());
        deliveryGroup.setGroupType(deliveryGroupRequestDto.getGroupType());
        deliveryGroup.setOrderStatus(OrderStatus.recruiting);

        deliveryGroup.setDeliveryDateTime(deliveryGroupRequestDto.getDeliveryDateTime());
        deliveryGroup.setPromotion(deliveryGroupRequestDto.getPromotion());
        deliveryGroup.setDay(deliveryGroupRequestDto.getDeliveryDateTime().getDayOfMonth());

        deliveryGroupService.save(deliveryGroup);

        Member member = memberService.findByUid(uid);
        member.setDeliveryGroup(deliveryGroup);
        member.changeGroup(deliveryGroup);
        memberService.save(member);

        Promotion promotion = deliveryGroupRequestDto.getPromotion();

        if (promotion.equals(Promotion.Off)) {

            //일반 주문 데이터
            Store store = storeService.findByStoreId(deliveryGroupRequestDto.getStoreId());

            MemberOrder memberOrder = new MemberOrder();
            memberOrder.setUid(uid);
            memberOrder.setStoreId(deliveryGroupRequestDto.getStoreId());
            memberOrder.setStore(store);
            memberOrder.setDeliveryGroup(deliveryGroup);
            deliveryGroupService.save(deliveryGroup);
            memberOrderService.save(memberOrder);
            memberService.save(member);

            List<MenuRequestDto> menuList = deliveryGroupRequestDto.getMenu();
            for (MenuRequestDto x : menuList) {

                OrderItem orderItem = new OrderItem();
                orderItem.setMenuName(x.getMenuName());
                orderItem.setCost(x.getCost());
                orderItem.setQuantity(x.getQuantity());

                orderItem.addOrderItemToMemberOrder(memberOrder);

                memberOrderService.save(memberOrder);
                orderItemService.save(orderItem);

            }

            memberOrder.setTotalCost(deliveryGroupRequestDto.getTotalCost());
            memberOrder.setPayMethod(deliveryGroupRequestDto.getPayMethod());
            memberOrder.setOrderTime(deliveryGroupRequestDto.getOrderTime());

            memberOrder.setMember(member);

            memberOrderService.save(memberOrder);
        }
        else {
            //프로모션 주문 데이터
            Store store = storeService.findByStoreId(deliveryGroupRequestDto.getStoreId());
            PromotionMenu promotionMenu = store.getPromotionMenu();

            MemberOrder memberOrder = new MemberOrder();
            memberOrder.setUid(uid);
            memberOrder.setStoreId(deliveryGroupRequestDto.getStoreId());
            memberOrder.setStore(store);
            memberOrder.setDeliveryGroup(deliveryGroup);
            deliveryGroupService.save(deliveryGroup);
            memberOrderService.save(memberOrder);
            memberService.save(member);

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuName(promotionMenu.getPromotionMenuName());
            orderItem.setCost(promotionMenu.getCost());
            orderItem.setQuantity(1);
            orderItem.addOrderItemToMemberOrder(memberOrder);

            memberOrderService.save(memberOrder);
            orderItemService.save(orderItem);


            memberOrder.setTotalCost(deliveryGroupRequestDto.getTotalCost());
            memberOrder.setPayMethod(deliveryGroupRequestDto.getPayMethod());
            memberOrder.setOrderTime(deliveryGroupRequestDto.getOrderTime());

            memberOrder.setMember(member);

            memberOrderService.save(memberOrder);
        }
    }

    @PostMapping("/deliveryGroupJoin")
    public Result addInGroup(@RequestBody DeliveryGroupRequestDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException, FirebaseMessagingException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        DeliveryGroup deliveryGroup = deliveryGroupService.findById(dto.getGroupId());
        member.setDeliveryGroup(deliveryGroup);
        member.changeGroup(deliveryGroup);
        memberService.save(member);


        if (deliveryGroup.getPromotion().equals(Promotion.Off)) {
            //주문 데이터
            MemberOrder memberOrder = new MemberOrder();

            Store store = storeService.findByStoreId(deliveryGroup.getStoreId());

            memberOrder.setUid(uid);
            memberOrder.setStoreId(store.getStoreId());
            memberOrder.setStore(store);
            memberOrder.setDeliveryGroup(deliveryGroup);
            deliveryGroupService.save(deliveryGroup);
            memberOrderService.save(memberOrder);

            List<MenuRequestDto> menuList = dto.getMenu();
            for (MenuRequestDto x : menuList) {

                OrderItem orderItem = new OrderItem();
                orderItem.setMenuName(x.getMenuName());
                orderItem.setCost(x.getCost());
                orderItem.setQuantity(x.getQuantity());

                orderItem.addOrderItemToMemberOrder(memberOrder);

                memberOrderService.save(memberOrder);
                orderItemService.save(orderItem);

            }
            memberOrder.setTotalCost(dto.getTotalCost());
            memberOrder.setPayMethod(dto.getPayMethod());
            memberOrder.setOrderTime(dto.getOrderTime());

            memberOrder.setMember(member);

            memberOrderService.save(memberOrder);

        }
        else {
            //프로모션 주문 데이터
            Store store = storeService.findByStoreId(deliveryGroup.getStoreId());
            PromotionMenu promotionMenu = store.getPromotionMenu();

            MemberOrder memberOrder = new MemberOrder();
            memberOrder.setUid(uid);
            memberOrder.setStoreId(store.getStoreId());
            memberOrder.setStore(store);
            memberOrder.setDeliveryGroup(deliveryGroup);
            deliveryGroupService.save(deliveryGroup);
            memberOrderService.save(memberOrder);
            memberService.save(member);

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuName(promotionMenu.getPromotionMenuName());
            orderItem.setCost(promotionMenu.getCost());
            orderItem.setQuantity(1);
            orderItem.addOrderItemToMemberOrder(memberOrder);

            memberOrderService.save(memberOrder);
            orderItemService.save(orderItem);

            memberOrder.setTotalCost(dto.getTotalCost());
            memberOrder.setPayMethod(dto.getPayMethod());
            memberOrder.setOrderTime(dto.getOrderTime());

            memberOrder.setMember(member);

            memberOrderService.save(memberOrder);
        }

        if (deliveryGroup.getCurrent() == deliveryGroup.getMaxValue()) {
            deliveryGroup.setOrderStatus(OrderStatus.recruitmentDone);
            memberService.save(member);
            deliveryGroupService.save(deliveryGroup);

            List<Member> fcmMember = deliveryGroup.getMemberList();;

            for (Member x : fcmMember) {
                Message message = Message.builder()
                        .setNotification(Notification.builder()
                                .setTitle("모집이 완료됐습니다!")
                                .setBody("모집이 완료 되었습니다.\n" + "배달 장소: " + deliveryGroup.getBuildingName() + "\n" + "배달 시간: " + deliveryGroup.getDeliveryDateTime())
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
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuildingName(), m.getDeliveryDateTime(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus(), m.getPromotion()))
                .collect(Collectors.toList());

        return new Result(collect);
    }


    @GetMapping("/dailyGroupList")
    public ResultList findDaily() {

        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByGroupTypeAndOrderStatus(GroupType.day, OrderStatus.recruiting);

        List<DeliveryGroupResponseDto> collect = deliveryGroup.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuildingName(), m.getDeliveryDateTime(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus(), m.getPromotion()))
                .collect(Collectors.toList());

//        List<Store> store = storeService.findStores();
        List<StoreResponseDto> dtoList = new ArrayList<>();
        for (DeliveryGroup x : deliveryGroup) {
            Store store = storeService.findByStoreId(x.getStoreId());
            List<Menu> menu = store.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription(), m.getImgUrl()))
                    .collect(Collectors.toList());

            List<Review> review = store.getReview();
            List<ReviewResponseDto> reviewList = review.stream()
                    .map(m -> new ReviewResponseDto(m.getReviewId(), m.getNickName(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                    .collect(Collectors.toList());

            StoreResponseDto dto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList, reviewList, store.getRate(), store.getNotice(), store.getStoreIntro(), store.getCategory(), store.getPromotion());
            dtoList.add(dto);
        }

        return new ResultList(collect, dtoList);
    }

    @GetMapping("/weeklyGroupList")
    public ResultList findWeekly() {
        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByGroupTypeAndOrderStatus(GroupType.weekly, OrderStatus.recruiting);

        List<DeliveryGroupResponseDto> collect = deliveryGroup.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuildingName(), m.getDeliveryDateTime(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus(), m.getPromotion()))
                .collect(Collectors.toList());

        List<StoreResponseDto> dtoList = new ArrayList<>();
        for (DeliveryGroup x : deliveryGroup) {
            Store store = storeService.findByStoreId(x.getStoreId());
            List<Menu> menu = store.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription(), m.getImgUrl()))
                    .collect(Collectors.toList());

            List<Review> review = store.getReview();
            List<ReviewResponseDto> reviewList = review.stream()
                    .map(m -> new ReviewResponseDto(m.getReviewId(), m.getNickName(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                    .collect(Collectors.toList());

            StoreResponseDto dto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList, reviewList, store.getRate(), store.getNotice(), store.getStoreIntro(), store.getCategory(), store.getPromotion());
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
        private T groupData;
        private U storeData;
    }
}
