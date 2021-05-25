package backend.nomad.domain.review;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long reviewId;
    private String contents;

    private String uid;
    private String imgUrl;
    private Double rate;

//    private List<OrderItem> orderItem = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    public Review() {

    }
    public Review(String contents, Store store, Member member, String uid, String imgUrl, Double rate) {
        this.contents = contents;
        this.store = store;
        this.uid = uid;
        this.member = member;
        this.imgUrl = imgUrl;
        this.rate = rate;
    }

    public void addMember(Member member) {
        this.member = member;
        member.getReview().add(this);
    }

    public void addStore(Store store) {
        this.store = store;
        store.getReview().add(this);
    }
}
