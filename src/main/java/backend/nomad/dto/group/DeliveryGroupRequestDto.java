package backend.nomad.dto.group;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.Location;
//import backend.nomad.domain.group.uid;
import backend.nomad.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryGroupRequestDto {
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
    @Enumerated(EnumType.STRING)
    private GroupType groupType;
}
