package backend.nomad.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

//    @OneToOne(mappedBy = "member")
//    private MemberOrder memberOrder;

//    @OneToMany(mappedBy = "member")
//    private List<MemberOrder> memberOrders = new ArrayList<>();

//    private MemberType memberType;
//    private String storeNum;
//    private String driveLicense;


    @Builder
    public Member(String email, String nickName, String phoneNum, String token, String uid, MemberType memberType, String shopIdNumber, String deliIdNumber, Long point) {
        this.email = email;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.token = token;
        this.uid = uid;
        this.memberType = memberType;
        this.shopIdNumber = shopIdNumber;
        this.deliIdNumber = deliIdNumber;
        this.point = point;
    }
}
