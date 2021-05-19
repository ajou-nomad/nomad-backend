package backend.nomad.dto.group;

import lombok.Getter;

@Getter
public class DeliveryGroupResponseDto {
    private Long groupId;
    private Long storeId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String building;
    private String time;
    private String date;
    private Integer current;
    private Integer maxValue;
    private Enum groupType;
    private String orderStatus;


    public DeliveryGroupResponseDto(Long groupId, Long storeId, Double latitude, Double longitude, String address, String building, String time, String date, Integer current, Integer maxValue, Enum groupType, String orderStatus) {
        this.groupId = groupId;
        this.storeId = storeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.building = building;
        this.time = time;
        this.date = date;
        this.current = current;
        this.maxValue = maxValue;
        this.groupType = groupType;
        this.orderStatus = orderStatus;
    }
}
