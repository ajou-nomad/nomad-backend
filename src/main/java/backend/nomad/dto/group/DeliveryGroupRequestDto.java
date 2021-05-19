package backend.nomad.dto.group;

import backend.nomad.domain.group.GroupType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryGroupRequestDto {
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
    private GroupType groupType;
    private String menuName;
    private Integer cost;
    private Integer quantity;
    private String payMethod;
    private String orderTime;
}
