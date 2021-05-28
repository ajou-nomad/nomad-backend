package backend.nomad.domain.member;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.likestore.LikeStore;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    private String email;
    private String nickName;
    private String phoneNum;
    private String token;
    private String uid;
    private Long point;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private String shopIdNumber;
    private String deliIdNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryGroup deliveryGroup;

    @OneToOne(mappedBy = "member")
    private Store store;

    @OneToMany(mappedBy = "member")
    private List<MemberOrder> memberOrder = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Chat> chat = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> review = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<LikeStore> likeStore = new ArrayList<>();


//    @OneToMany(mappedBy = "member")
//    private List<MemberOrder> memberOrders = new ArrayList<>();

//    private MemberType memberType;
//    private String storeNum;
//    private String driveLicense;

    public Member() {
    }

    public Member(String email, String nickName, String phoneNum, String token, String uid, MemberType memberType, String shopIdNumber, String deliIdNumber, Long point, DeliveryGroup deliveryGroup) {
        this.email = email;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.token = token;
        this.uid = uid;
        this.memberType = memberType;
        this.shopIdNumber = shopIdNumber;
        this.deliIdNumber = deliIdNumber;
        this.point = point;
        if (deliveryGroup != null) {
            changeGroup(deliveryGroup);
        }
    }
    public void changeGroup(DeliveryGroup deliveryGroup) {
        this.deliveryGroup = deliveryGroup;
        deliveryGroup.getMemberList().add(this);
        deliveryGroup.setCurrent(deliveryGroup.getMemberList().size());
    }

    public void deleteGroup(DeliveryGroup deliveryGroup) {
        this.deliveryGroup = deliveryGroup;
        deliveryGroup.getMemberList().remove(this);
    }

}
