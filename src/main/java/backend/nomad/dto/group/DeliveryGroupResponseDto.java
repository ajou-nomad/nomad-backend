package backend.nomad.dto.group;

import backend.nomad.domain.group.Location;
//import backend.nomad.domain.group.uid;
import backend.nomad.domain.member.Member;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class DeliveryGroupResponseDto {
    private Long groupId;
    private String storeId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String building;
    private String time;
    private String date;
    private Integer current;
    private Integer maxValue;
    private Enum groupType;

    public DeliveryGroupResponseDto(Long groupId, String storeId, Double latitude, Double longitude, String address, String building, String time, String date, Integer current, Integer maxValue, Enum groupType) {
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
    }
}
