package backend.nomad.dto.group;

import backend.nomad.domain.group.OrderStatus;
import lombok.Getter;

@Getter
public class GroupOrderRequestDto {
    private Long groupId;
    private OrderStatus orderStatus;
}
