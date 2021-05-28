package backend.nomad.domain.group;

//import backend.nomad.domain.member.MemberOrder;

import backend.nomad.domain.TimeEntity;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class DeliveryGroup {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long groupId;
    private Long storeId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String buildingName;
    private Integer current;
    private Integer maxValue;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime deliveryDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @OneToMany(mappedBy = "deliveryGroup", fetch = FetchType.EAGER)
    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "deliveryGroup" , fetch = FetchType.LAZY)
    private List<MemberOrder> memberOrders = new ArrayList<>();


    public DeliveryGroup(Long storeId, Double latitude, Double longitude, String address, String buildingName, LocalDateTime deliveryDateTime, Integer current, Integer maxValue, GroupType groupType, OrderStatus orderStatus) {
        this.storeId = storeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.deliveryDateTime = deliveryDateTime;
        this.current = current;
        this.maxValue = maxValue;
        this.groupType = groupType;
        this.orderStatus = orderStatus;
    }

    public DeliveryGroup() {

    }

//    public DeliveryGroup(LocalDateTime localDateTime) {
//        this.localDateTime = LocalDateTime.now();
//    }

//    public int addCurrent(DeliveryGroup deliveryGroup) {
//        this.current = current;
//        ().add(this.member.getUid());
//    }
}