package backend.nomad.domain.likestore;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class LikeStore {

    @Id
    @GeneratedValue
    @Column(name = "likeStore_id")
    private Long likeStoreId;

    private String uid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    public LikeStore() {
    }

    public void addLikeToMember(Member member) {
        this.member = member;
        member.getLikeStore().add(this);
    }

    public void addLikeToStore(Store store) {
        this.store = store;
        store.getLikeStore().add(this);
    }
}
