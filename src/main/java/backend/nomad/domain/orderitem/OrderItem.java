package backend.nomad.domain.orderitem;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.store.Menu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberOrder memberOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    private Integer quantity;

    public OrderItem() {

    }
    public OrderItem(MemberOrder memberOrder, Menu menu, Integer quantity) {
        this.memberOrder = memberOrder;
        this.menu = menu;
        this.quantity = quantity;
    }

    public void addOrderItemToMemberOrder(MemberOrder memberOrder) {
        this.memberOrder = memberOrder;
        memberOrder.getOrderItem().add(this);
    }

    public void addOrderItemToMenu(Menu menu) {
        this.menu = menu;
        menu.getOrderItem().add(this);
    }
}
