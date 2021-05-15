package backend.nomad.dto.group;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryGroupSaveRequestDto {
    private Long groupNum;
    private Location location;
    private String store;

    @Builder
    public DeliveryGroupSaveRequestDto(Long groupNum, Location location, String store) {
        this.groupNum = groupNum;
        this.location = location;
        this.store = store;
    }

    public DeliveryGroup toEntity() {
        return DeliveryGroup.builder()
                .groupNum(groupNum)
                .location(location)
                .store(store)
                .build();

    }
}
