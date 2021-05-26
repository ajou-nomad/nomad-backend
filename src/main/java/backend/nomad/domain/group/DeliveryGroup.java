package backend.nomad.domain.group;

//import backend.nomad.domain.member.MemberOrder;

import backend.nomad.domain.TimeEntity;
import backend.nomad.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

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
    private String time;
    private String date;
    private Integer current;
    private Integer maxValue;
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

//    @OneToMany(mappedBy = "deliveryGroup")
//    @ElementCollection
//    private List<String> memberList;

    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @OneToMany(mappedBy = "deliveryGroup")
    private List<Member> memberList = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberOrder_id")
//    private MemberOrder memberOrder;

    public DeliveryGroup(Long storeId, Double latitude, Double longitude, String address, String buildingName, String time, String date, Integer current, Integer maxValue, GroupType groupType, OrderStatus orderStatus) {
        this.storeId = storeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.time = time;
        this.date = date;
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