//package backend.nomad.domain.store;
//
//import backend.nomad.domain.member.Member;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Setter
//@Getter
//public class Store {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "store_id")
//    private Long storeId;
//
//    private String storeName;
//    private String PhoneNumber;
//
//    private String storeAddress;
//    private Double latitude;
//    private Double longitude;
//
//    private String openTime;
//    private String closeTime;
//    private String deliveryTip;
////    private String logoUrl;
//
////    @OneToMany(mappedBy = "store")
////    private List<Menu> menuList;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private Member member;
//
//    public Store() {
//    }
//
//    public Store(String storeName, String phoneNumber, String storeAddress, Double latitude, Double longitude, String openTime, String closeTime, String deliveryTip, List<Menu> menuList, Member member) {
//        this.storeName = storeName;
//        this.PhoneNumber = phoneNumber;
//        this.storeAddress = storeAddress;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.openTime = openTime;
//        this.closeTime = closeTime;
//        this.deliveryTip = deliveryTip;
//    }
//
//}
