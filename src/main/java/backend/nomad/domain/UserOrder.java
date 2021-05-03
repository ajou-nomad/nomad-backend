package backend.nomad.domain;

import javax.persistence.*;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue
    @Column(name = "user_order_id")
    private Long userOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}
