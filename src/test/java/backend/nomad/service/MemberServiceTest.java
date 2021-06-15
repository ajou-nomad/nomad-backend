package backend.nomad.service;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setNickName("LEE");

        Long memberId = memberService.save(member);

        if (memberService.findById(memberId) != null) {
            System.out.println("회원가입 완료");
            System.out.println("닉네임: " + member.getNickName());
        }

        assertEquals(member, memberService.findById(memberId));
    }

    @Test
    public void 파이어베이스_회원가입_유무확인() {
        Member member = new Member();
        member.setUid("uid");

        memberService.save(member);

        assertEquals(member, memberService.findByUid("uid"));
    }

}