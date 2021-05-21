package backend.nomad.service;

import backend.nomad.domain.member.Chat;
import backend.nomad.domain.member.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Long save(Chat chat) {
        return chatRepository.save(chat).getChatId();
    }
}
