package backend.nomad.domain.member;

import backend.nomad.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MemberChat {

    @Id
    @GeneratedValue
    @Column(name = "memberChat_id")
    private Long memberChatId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void addMemberChatToMember(Member member) {
        this.member = member;
        member.getMemberChat().add(this);
    }

    public void addMemberChatToChat(Chat chat) {
        this.chat = chat;
        chat.getMemberChat().add(this);
    }
}
