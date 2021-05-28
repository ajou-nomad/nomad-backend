package backend.nomad.domain.orderitem;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.store.Menu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "orderItem_id")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberOrder memberOrder;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Menu menu;
    private Integer cost;
    private String menuName;

    private Integer quantity;

    public OrderItem() {

    }
    public OrderItem(MemberOrder memberOrder, Integer cost, Integer quantity, String menuName) {
        this.memberOrder = memberOrder;
//        this.menu = menu;
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public void addOrderItemToMemberOrder(MemberOrder memberOrder) {
        this.memberOrder = memberOrder;
        memberOrder.getOrderItem().add(this);
    }

//    public void addOrderItemToMenu(Menu menu) {
//        this.menu = menu;
//        menu.getOrderItem().add(this);
//    }
}
