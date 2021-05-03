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

    private String nickname;

    private String userEmail;

    private String phoneNumber;

    private Date created;

    private String token;

    private Long Point;

    @OneToMany(mappedBy = "user")
    private List<UserOrder> userorders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> review = new ArrayList<>();
}
