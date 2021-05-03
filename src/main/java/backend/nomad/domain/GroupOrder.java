package backend.nomad.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class GroupOrder {

    @Id
    @GeneratedValue
    @Column(name = "group_order_id")
    private Long groupOrderId;

    private Long groupId;

    private Long storeId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
