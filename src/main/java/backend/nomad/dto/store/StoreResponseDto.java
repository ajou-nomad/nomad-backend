package backend.nomad.dto.store;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import backend.nomad.dto.review.ReviewResponseDto;
import lombok.Getter;

import java.util.ArrayList;
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
    private List<MenuResponseDto> menu;
    private List<ReviewResponseDto> reviewResponseDto;
    private Double rate;

    private String notice;
    private String storeIntro;
    private String category;


    public StoreResponseDto(Long storeId, String storeName, String phoneNumber, String address, Double latitude, Double longitude, String openTime, String closeTime, Integer deliveryTip, String logoUrl, List<MenuResponseDto> menu, List<ReviewResponseDto> reviewResponseDto, Double rate) {
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
        this.reviewResponseDto = reviewResponseDto;
    }

    public StoreResponseDto(Long storeId, String storeName, String phoneNumber, String address, Double latitude, Double longitude, String openTime, String closeTime, Integer deliveryTip, String logoUrl, List<MenuResponseDto> menu, List<ReviewResponseDto> reviewResponseDto, Double rate, String notice, String storeIntro, String category) {
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
        this.reviewResponseDto = reviewResponseDto;
        this.notice = notice;
        this.storeIntro = storeIntro;
        this.category = category;
    }

}
