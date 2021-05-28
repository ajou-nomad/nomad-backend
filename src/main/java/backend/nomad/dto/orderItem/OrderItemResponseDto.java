package backend.nomad.dto.orderItem;

import backend.nomad.domain.member.MemberOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderItemResponseDto {

    private List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
    private Long orderItemId;
    private Integer cost;
    private String menuName;

    private Integer quantity;

    public OrderItemResponseDto() {

    }
    public OrderItemResponseDto(Long orderItemId, String menuName, Integer cost, Integer quantity) {
        this.orderItemId = orderItemId;
        this.menuName = menuName;
        this.cost = cost;
        this.quantity = quantity;
    }

    public OrderItemResponseDto(List<OrderItemResponseDto> orderItemResponseDtoList) {
        this.orderItemResponseDtoList = orderItemResponseDtoList;
    }
}
