package backend.nomad.dto.store;

import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.dto.member.MemberOrderResponseDto;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DeliveryGroupDto {
    private Long groupId;
    private Long storeId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String buildingName;
    private OrderStatus orderStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime deliveryDateTime;

    private List<MemberOrderDto> orderItemList;

    public DeliveryGroupDto(Long groupId, Long storeId, Double latitude, Double longitude, String address, String buildingName, LocalDateTime deliveryDateTime, OrderStatus orderStatus, List<MemberOrderDto> orderItemList) {
        this.groupId = groupId;
        this.storeId = storeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.deliveryDateTime = deliveryDateTime;
        this.orderStatus = orderStatus;
        this.orderItemList = orderItemList;
    }
}
