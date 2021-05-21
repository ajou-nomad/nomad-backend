package backend.nomad.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Chat {

    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private Long ChatId;

    private String ChatName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void addChat(Member member) {
        this.member = member;
        member.getChat().add(this);
    }
}
