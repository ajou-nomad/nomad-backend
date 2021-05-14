package backend.nomad.dto.group;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class DeliveryGroupMainResponseDto {
    private Long groupId;
    private Long groupNum;
    private String address;
    private String store;

    public DeliveryGroupMainResponseDto(Long groupId, Long groupNum, String address, String store) {
        this.groupId = groupId;
        this.groupNum = groupNum;
        this.address = address;
        this.store = store;
    }
}
