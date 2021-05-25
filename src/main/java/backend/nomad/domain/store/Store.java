package backend.nomad.domain.store;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.review.Review;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Store {

    @Id
    @GeneratedValue
    @Column(name = "store_id")
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

    @OneToMany(mappedBy = "store")
    private List<Menu> menu = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<MemberOrder> memberOrder = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    private Double rate;

    @OneToMany(mappedBy = "store")
    private List<Review> review = new ArrayList<>();

    public Store() {
    }

    public Store(String storeName, String phoneNumber, String address, Double latitude, Double longitude, String openTime, String closeTime, Integer deliveryTip, String logoUrl, Member member, Double rate) {
        this.storeName = storeName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.deliveryTip = deliveryTip;
        this.logoUrl = logoUrl;
        this.member = member;
        this.rate = rate;
    }

    public void setMember(Member member) {
        this.member = member;
        member.setStore(this);
    }

}
