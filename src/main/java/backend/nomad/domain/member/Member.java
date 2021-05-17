package backend.nomad.domain.member;

import backend.nomad.domain.group.DeliveryGroup;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

//    @OneToOne(mappedBy = "member")
//    private Store store;

    @OneToMany(mappedBy = "member")
    private List<MemberOrder> memberOrder = new ArrayList<>();

//    private MemberType memberType;
//    private String storeNum;
//    private String driveLicense;


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
        System.out.println("성공");
        deliveryGroup.getMemberList().add(this);
        deliveryGroup.setCurrent(deliveryGroup.getMemberList().size());
    }

    public Member() {

    }
}
