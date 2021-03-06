package backend.nomad.dto.store;

import backend.nomad.domain.store.Promotion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreRequestDto {

    private Long storeId;
    private String storeName;
    private String phoneNumber;

    private String address;
    private Double latitude;
    private Double longitude;

    private String openTime;
    private String closeTime;
    private Integer deliveryTip;
    private String logoUrl;

    private String notice;
    private String storeIntro;
    private String category;

    private Promotion promotion;
}
