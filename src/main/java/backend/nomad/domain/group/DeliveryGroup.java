package backend.nomad.domain.group;

//import backend.nomad.domain.member.MemberOrder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class DeliveryGroup {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long groupId;
    private Long groupNum;

    @Embedded
    private Location location;

    private String store;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberOrder_id")
//    private MemberOrder memberOrder;


    @Builder
    public DeliveryGroup(Long groupNum, Location location, String store) {
        this.groupNum = groupNum;
        this.location = location;
        this.store = store;
    }
}