package backend.nomad.dto.store;

import backend.nomad.domain.group.OrderStatus;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import backend.nomad.dto.review.ReviewResponseDto;

import java.util.List;

public class MemberOrderDto {

    private Long memberOrderId;
    private List<OrderItemDto> orderItemList;

    public MemberOrderDto(Long memberOrderId, List<OrderItemDto> orderItemList) {
        this.memberOrderId = memberOrderId;
        this.orderItemList = orderItemList;
    }
}
