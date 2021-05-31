package backend.nomad.domain.member;

import backend.nomad.domain.group.DeliveryGroup;
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
    private Long chatId;

    private String chatToken;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(mappedBy = "chat")
    private DeliveryGroup deliveryGroup;

    public void addChat(Member member) {
        this.member = member;
        member.getChat().add(this);
    }

    public void deleteMember(Member member) {
        this.member = member;
        member.getChat().remove(this);
    }
}
