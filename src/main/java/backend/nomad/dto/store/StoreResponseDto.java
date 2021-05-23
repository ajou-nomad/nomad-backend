package backend.nomad.dto.store;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.store.Menu;
import lombok.Getter;

import java.util.List;

@Getter
public class StoreResponseDto {

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
    private List<Menu> menu;


    public StoreResponseDto(Long storeId, String storeName, String phoneNumber, String address, Double latitude, Double longitude, String openTime, String closeTime, Integer deliveryTip, String logoUrl, List<Menu> menu) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.deliveryTip = deliveryTip;
        this.logoUrl = logoUrl;
        this.menu = menu;
    }
}
