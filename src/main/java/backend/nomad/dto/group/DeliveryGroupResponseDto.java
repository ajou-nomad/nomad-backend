package backend.nomad.dto.group;

import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.OrderStatus;
import lombok.Getter;

@Getter
public class DeliveryGroupResponseDto {
    private Long groupId;
    private Long storeId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String buildingName;
    private String time;
    private String date;
    private Integer current;
    private Integer maxValue;
    private GroupType groupType;
    private OrderStatus orderStatus;


    public DeliveryGroupResponseDto(Long groupId, Long storeId, Double latitude, Double longitude, String address, String buildingName, String time, String date, Integer current, Integer maxValue, GroupType groupType, OrderStatus orderStatus) {
        this.groupId = groupId;
        this.storeId = storeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.time = time;
        this.date = date;
        this.current = current;
        this.maxValue = maxValue;
        this.groupType = groupType;
        this.orderStatus = orderStatus;
    }
}
