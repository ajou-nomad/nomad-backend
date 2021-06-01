package backend.nomad.service;

import backend.nomad.domain.likestore.LikeStore;
import backend.nomad.domain.likestore.LikeStoreRepository;
import backend.nomad.domain.member.MemberChat;
import backend.nomad.domain.member.MemberChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberChatService {

    private final MemberChatRepository memberChatRepository;

    @Transactional
    public Long save(MemberChat memberChat) {
        return memberChatRepository.save(memberChat).getMemberChatId();
    }

//    @Transactional
//    public List<MemberChat> findByUid(String uid) {
//        return memberChatRepository.findByUid(uid);
//    }
}
