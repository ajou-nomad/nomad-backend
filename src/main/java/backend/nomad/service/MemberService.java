package backend.nomad.service;

//import backend.nomad.domain.User;
import backend.nomad.domain.member.MemberRepository;
import backend.nomad.dto.member.MemberMainResponseDto;
import backend.nomad.dto.member.MemberSaveRequestDto;
//import backend.nomad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto dto) {
        return memberRepository.save(dto.toEntity()).getId();
    }

//    public List<User> findMembers() {
//        return userRepository.findAll();
//    }

//    public User findOne(Long memberId) {
//        return userRepository.findOne(memberId);
//    }
}
