package backend.nomad.domain.store;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
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
    private String PhoneNumber;

    private String address;
    private Double latitude;
    private Double longitude;

    private String openTime;
    private String closeTime;
    private String deliveryTip;
    private String logoUrl;

    @OneToMany(mappedBy = "store")
    private List<Menu> menu = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<MemberOrder> memberOrder = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Store() {
    }

    public Store(String storeName, String phoneNumber, String address, Double latitude, Double longitude, String openTime, String closeTime, String deliveryTip, String logoUrl, Member member) {
        this.storeName = storeName;
        this.PhoneNumber = phoneNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.deliveryTip = deliveryTip;
        this.logoUrl = logoUrl;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getStore().add(this);
    }
}
