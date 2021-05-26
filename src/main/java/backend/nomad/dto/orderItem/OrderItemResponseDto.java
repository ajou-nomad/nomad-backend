package backend.nomad.dto.orderItem;

import backend.nomad.domain.member.MemberOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class OrderItemResponseDto {

    private Integer cost;
    private String menuName;

    private Integer quantity;

    public OrderItemResponseDto() {

    }
    public OrderItemResponseDto(String menuName, Integer cost, Integer quantity) {
        this.menuName = menuName;
        this.cost = cost;
        this.quantity = quantity;
    }
}
