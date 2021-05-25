package backend.nomad.dto.member;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Store;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberOrderResponseDto {

    private String storeName;
    private List<OrderItem> orderItem;
    private Integer totalCost;
    private String payMethod;
    private String orderTime;

    public MemberOrderResponseDto(String storeName, List<OrderItem> orderItem, Integer totalCost, String payMethod, String orderTime) {
        this.storeName = storeName;
        this.orderItem = orderItem;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
        this.orderTime = orderTime;
    }
}
