package backend.nomad.service;

import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.member.MemberOrderRepository;
import backend.nomad.domain.member.MemberRepository;
import backend.nomad.dto.member.MemberOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberOrderService {

    private final MemberRepository memberRepository;
    private final MemberOrderRepository memberOrderRepository;

    @Transactional
    public Long save(MemberOrder memberOrder) {
        return memberOrderRepository.save(memberOrder).getMemberOrderId();
    }

    @Transactional
    public List<MemberOrder> findMemberOrders() {
        return memberOrderRepository.findAll();
    }

    @Transactional
    public MemberOrder findByStoreId(Long storeId) {
        return memberOrderRepository.findByStoreId(storeId);
    }
}
