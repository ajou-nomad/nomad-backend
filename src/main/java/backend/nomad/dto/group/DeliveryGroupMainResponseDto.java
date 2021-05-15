package backend.nomad.dto.group;

import backend.nomad.domain.group.Location;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class DeliveryGroupMainResponseDto {
    private Long groupId;
    private Long groupNum;
    private Location location;
    private String store;

    public DeliveryGroupMainResponseDto(Long groupId, Long groupNum, Location location, String store) {
        this.groupId = groupId;
        this.groupNum = groupNum;
        this.location = location;
        this.store = store;
    }
}
