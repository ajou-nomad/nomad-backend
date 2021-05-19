package backend.nomad.domain.member;

import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class MemberOrder {

    @Id
    @GeneratedValue
    private Long memberOrderId;

    private String uid;
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @OneToMany(mappedBy = "memberOrder")
    private List<OrderItem> orderItem = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Integer totalCost;
    private String payMethod;

    private String orderTime;

    public MemberOrder(Store store, String uid, Long storeId, Member member, Integer totalCost, String payMethod, String orderTime) {
        this.store = store;
        this.uid = uid;
        this.storeId = storeId;
        this.member = member;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
        this.orderTime = orderTime;
    }

    public MemberOrder() {

    }

    public void setMember(Member member) {
        this.member = member;
        member.getMemberOrder().add(this);
    }

    public void setStore(Store store) {
        this.store = store;
        store.getMemberOrder().add(this);
    }
}
