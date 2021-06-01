package backend.nomad.dto.chat;

import lombok.Getter;

@Getter
public class ChatDto {
    private Long memberId;
    private String chatId;

    public ChatDto(Long memberId, String chatId) {
        this.memberId = memberId;
        this.chatId = chatId;
    }
}
