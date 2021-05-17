//package backend.nomad.domain.orderitem;
//
//import backend.nomad.domain.member.MemberOrder;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@Entity
//public class OrderItem {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "order_item_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_order_id")
//    private MemberOrder memberOrder;
//
//    private static  orderItems;
//}
