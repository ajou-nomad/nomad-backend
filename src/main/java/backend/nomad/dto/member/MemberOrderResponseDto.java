package backend.nomad.dto.member;

import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreResponseDto;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberOrderResponseDto {

    private Long memberOrderId;
    private Long storeId;
    private String storeName;
    private List<OrderItemResponseDto> orderItemList;
    private List<ReviewResponseDto> reviewList;
    private OrderStatus orderStatus;
    private Integer totalCost;
    private String payMethod;
    private String orderTime;

    public MemberOrderResponseDto(Long storeId, String storeName, List<OrderItemResponseDto> orderItemList, List<ReviewResponseDto> reviewList, Integer totalCost, String payMethod, String orderTime) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.orderItemList = orderItemList;
        this.reviewList = reviewList;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
        this.orderTime = orderTime;
    }

    public MemberOrderResponseDto(Long memberOrderId, Long storeId, String storeName, OrderStatus orderStatus, List<OrderItemResponseDto> orderItemList, List<ReviewResponseDto> reviewList, Integer totalCost, String payMethod, String orderTime) {
        this.memberOrderId = memberOrderId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.orderStatus = orderStatus;
        this.orderItemList = orderItemList;
        this.reviewList = reviewList;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
        this.orderTime = orderTime;
    }

    public MemberOrderResponseDto(Long memberOrderId, List<OrderItemResponseDto> orderItemList) {
        this.memberOrderId = memberOrderId;
        this.orderItemList = orderItemList;
    }
}
