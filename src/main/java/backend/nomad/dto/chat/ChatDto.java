package backend.nomad.dto.chat;

import lombok.Getter;

@Getter
public class ChatDto {
    private Long memberId;
    private String chatId;
    private Long groupId;

    public ChatDto(Long memberId, String chatId, Long groupId) {
        this.memberId = memberId;
        this.chatId = chatId;
        this.groupId = groupId;
    }
}
