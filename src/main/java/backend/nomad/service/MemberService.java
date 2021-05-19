package backend.nomad.service;

//import backend.nomad.domain.User;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member).getMemberId();
    }

    @Transactional
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member findByUid(String uid) {
        return memberRepository.findByUid(uid);
    }

//    public List<User> findMembers() {
//        return userRepository.findAll();
//    }

//    public User findOne(Long memberId) {
//        return userRepository.findOne(memberId);
//    }
}
