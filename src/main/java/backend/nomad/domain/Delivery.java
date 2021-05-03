package backend.nomad.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long groupId;


    private int groupNum;
    private String address;
    private Date delivertDate;
}
