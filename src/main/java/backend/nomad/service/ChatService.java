package backend.nomad.service;

import backend.nomad.domain.member.Chat;
import backend.nomad.domain.member.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public Long save(Chat chat) {
        return chatRepository.save(chat).getChatId();
    }

    @Transactional
    public void delete(Chat chat) {
        chatRepository.delete(chat);
    }
}
