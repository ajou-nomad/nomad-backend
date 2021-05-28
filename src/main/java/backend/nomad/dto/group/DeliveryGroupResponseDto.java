package backend.nomad.dto.group;

import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.dto.orderItem.OrderItemResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DeliveryGroupResponseDto {
    private Long groupId;
    private Long storeId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String buildingName;
    private Integer current;
    private Integer maxValue;
    private GroupType groupType;
    private OrderStatus orderStatus;
    private LocalDateTime deliveryDateTime;

    private OrderItemResponseDto orderItemList;


    public DeliveryGroupResponseDto(Long groupId, Long storeId, Double latitude, Double longitude, String address, String buildingName, LocalDateTime deliveryDateTime, Integer current, Integer maxValue, GroupType groupType, OrderStatus orderStatus) {
        this.groupId = groupId;
        this.storeId = storeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.deliveryDateTime = deliveryDateTime;
        this.current = current;
        this.maxValue = maxValue;
        this.groupType = groupType;
        this.orderStatus = orderStatus;
    }

    public DeliveryGroupResponseDto(Long groupId, Long storeId, Double latitude, Double longitude, String address, String buildingName, LocalDateTime deliveryDateTime, Integer current, Integer maxValue, GroupType groupType, OrderStatus orderStatus, OrderItemResponseDto orderItemList) {
        this.groupId = groupId;
        this.storeId = storeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.deliveryDateTime = deliveryDateTime;
        this.current = current;
        this.maxValue = maxValue;
        this.groupType = groupType;
        this.orderStatus = orderStatus;
        this.orderItemList = orderItemList;
    }
}
