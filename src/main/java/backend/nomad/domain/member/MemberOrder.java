package backend.nomad.domain.member;

import backend.nomad.domain.group.DeliveryGroup;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MemberOrder {

    @Id
    @GeneratedValue
    private Long memberOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @OneToMany
//    private DeliveryGroup deliveryGroup;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "orderItems")
//    private List<String> orderItems = new ArrayList<>();

//    public void setMember(Member member) {
//        this.member = member;
//        member.getMemberOrder().add(this);
//    }
//    public static MemberOrder createMemberOrder(Member member) {
//        MemberOrder memberOrder = new MemberOrder();
//        memberOrder.setMember(member);
////        for (OrderItem orderItem : orderItem)
////        this.deliveryGroup = deliveryGroup;
//        return memberOrder;
//    }
    @Builder
    public MemberOrder(Member member) {
        this.member = member;

    }
}
