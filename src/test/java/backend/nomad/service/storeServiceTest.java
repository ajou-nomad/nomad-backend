package backend.nomad.service;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberType;
import backend.nomad.domain.store.Store;
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
public class storeServiceTest {

    @Autowired
    StoreService storeService;

    @Autowired
    MemberService memberService;

    @Test
    public void 매장등록() {

        Member member = new Member();
        member.setUid("LEE");
        member.setMemberType(MemberType.Shop);

        memberService.save(member);

        if (memberService.findByUid("LEE") != null) {
            System.out.println("사용자 가입 완료");
            System.out.println("사용자 uid: " + member.getUid());
        }
        else {
            System.out.println("사용자 가입 실패");
        }

        Store store = new Store();
        store.setMember(member);
        store.setStoreName("빽다방");

        storeService.save(store);

        if (storeService.findByStoreName("빽다방") != null) {
            System.out.println("매장 등록 완료");
            System.out.println("매장 이름은:" + store.getStoreName());
        }
        else {
            System.out.println("매장 등록 실패");
        }
    }
}
