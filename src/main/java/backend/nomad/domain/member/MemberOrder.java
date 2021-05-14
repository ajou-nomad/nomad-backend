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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @OneToMany
//    private DeliveryGroup deliveryGroup;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "orderItems")
//    private List<String> orderItems = new ArrayList<>();

    @Builder
    public MemberOrder(Member member) {
        this.member = member;
//        this.deliveryGroup = deliveryGroup;
    }
}
