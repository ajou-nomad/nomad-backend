package backend.nomad.dto.group;

import backend.nomad.domain.group.DeliveryGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryGroupSaveRequestDto {
    private Long groupNum;
    private String address;
    private String store;

    @Builder
    public DeliveryGroupSaveRequestDto(Long groupNum, String address, String store) {
        this.groupNum = groupNum;
        this.address = address;
        this.store = store;
    }

    public DeliveryGroup toEntity() {
        return DeliveryGroup.builder()
                .groupNum(groupNum)
                .address(address)
                .store(store)
                .build();

    }
}
