package backend.nomad.service;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.orderitem.OrderItem;
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
public class MemberOrderServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberOrderService memberOrderService;

    @Autowired
    OrderItemService orderItemService;

    @Test
    public void 주문() {
        Member member = new Member();
        member.setUid("LEE");
        memberService.save(member);

        if (memberService.findByUid("LEE") != null) {
            System.out.println("사용자 가입 완료");
        }
        else {
            System.out.println("사용자 가입 실패");
        }

        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMember(member);
        Long id = memberOrderService.save(memberOrder);

        if (memberOrderService.findById(id) != null) {
            System.out.println("사용자 주문 완료");
        }
        else {
            System.out.println("사용자 주문 실패");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuName("커피");
        orderItem.setMemberOrder(memberOrder);

        Long itemId = orderItemService.save(orderItem);

        if (orderItemService.findById(itemId) != null) {
            System.out.println("사용자 주문 아이템 매핑 완료");
        }
        else {
            System.out.println("사용자 주문 아이템 매핑 실패");
        }
    }

}