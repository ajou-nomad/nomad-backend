package backend.nomad.dto.store;

import backend.nomad.domain.store.Promotion;
import backend.nomad.dto.review.ReviewResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreListDto {


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
    private List<MenuResponseDto> menu;
    private List<ReviewResponseDto> reviewList;
    private Double rate;

    private String notice;
    private String storeIntro;
    private String category;
    private Promotion promotion;

    public StoreListDto(Long storeId, String storeName, String phoneNumber, String address, Double latitude, Double longitude, String openTime, String closeTime, Integer deliveryTip, String logoUrl, List<MenuResponseDto> menu, List<ReviewResponseDto> reviewList, Double rate, String notice, String storeIntro, String category, Promotion promotion) {
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
        this.rate = rate;
        this.reviewList = reviewList;
        this.notice = notice;
        this.storeIntro = storeIntro;
        this.category = category;
        this.promotion = promotion;
    }
}
