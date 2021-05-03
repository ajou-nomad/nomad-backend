package backend.nomad.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ReviewReply {

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    private Store store;

    private String replyComment;

    private Date created;
}
