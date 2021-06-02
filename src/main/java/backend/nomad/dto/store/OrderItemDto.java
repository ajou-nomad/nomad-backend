package backend.nomad.dto.store;

import lombok.Getter;

@Getter
public class OrderItemDto {
    private String menuName;
    private Double cost;
    private Integer quantity;

    public OrderItemDto(String menuName, Double cost, Integer quantity) {
        this.menuName = menuName;
        this.cost = cost;
        this.quantity = quantity;
    }
}
