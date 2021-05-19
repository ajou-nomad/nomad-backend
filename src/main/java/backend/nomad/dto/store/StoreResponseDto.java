package backend.nomad.dto.store;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.store.Menu;
import lombok.Getter;

@Getter
public class StoreResponseDto {

    private Long storeId;

    private String storeName;
    private String PhoneNumber;

    private String address;
    private Double latitude;
    private Double longitude;

    private String openTime;
    private String closeTime;
    private String deliveryTip;
//    private String logoUrl;


    public StoreResponseDto(Long storeId, String storeName, String phoneNumber, String address, Double latitude, Double longitude, String openTime, String closeTime, String deliveryTip) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.PhoneNumber = phoneNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.deliveryTip = deliveryTip;
    }
}
