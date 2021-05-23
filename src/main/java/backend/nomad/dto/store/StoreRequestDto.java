package backend.nomad.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreRequestDto {

    private String storeName;
    private String phoneNumber;

    private String address;
    private Double latitude;
    private Double longitude;

    private String openTime;
    private String closeTime;
    private Integer deliveryTip;
    private String logoUrl;

}
