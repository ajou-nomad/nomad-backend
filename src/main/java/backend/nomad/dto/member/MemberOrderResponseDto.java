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

    private Long memberOrderId;
    private Store store;
    private Member member;
    private Integer totalCost;
    private String payMethod;
    private String orderTime;

    public MemberOrderResponseDto(Long memberOrderId, Store store, Member member, Integer totalCost, String payMethod, String orderTime) {
        this.memberOrderId = memberOrderId;
        this.store = store;
        this.member = member;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
        this.orderTime = orderTime;
    }
}
