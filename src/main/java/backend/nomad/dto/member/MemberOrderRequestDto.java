package backend.nomad.dto.member;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberOrderRequestDto {
    private Long groupId;
    private Long storeId;
    private String uid;
    private Menu menu;
    private Integer totalCost;
    private String payMethod;
    private String orderTime;
    private String menuName;
    private Integer cost;
    private Integer quantity;

}
