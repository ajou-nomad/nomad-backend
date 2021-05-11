package backend.nomad.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

//    private String nickName;
//
//    private String userEmail;
//
//    private String phoneNumber;
//
//    private Date created;
//
//    private String token;
//
//    private Long Point;

    private String firstname;
    private String lastname;

//    @OneToMany(mappedBy = "user")
//    private List<UserOrder> userOrders = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<Review> review = new ArrayList<>();
}
