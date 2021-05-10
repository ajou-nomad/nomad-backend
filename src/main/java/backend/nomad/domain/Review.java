//package backend.nomad.domain;
//
//import lombok.Getter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Getter
//public class Review {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "review_id")
//    private Long reviewId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "review")
//    private User user;
//
//    private Long userId;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")
//    private Long userOrderId;
//    private Long reviewComment;
//    private Date created;
//    private double reviewStar;
//
//    @OneToMany(mappedBy = "review")
//    private List<ReviewReply> reviewReply = new ArrayList<>();
//
//
//}
