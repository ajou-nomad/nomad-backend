package backend.nomad.service;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.orderitem.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Long save(OrderItem OrderItem) {
        return orderItemRepository.save(OrderItem).getOrderItemId();
    }

    @Transactional
    public List<OrderItem> findOrderItem() {
        return orderItemRepository.findAll();
    }

//    @Transactional
//    public OrderItem findByUid(String uid) {
//        return orderItemRepository.findByUid(uid);
//    }

    @Transactional
    public OrderItem findById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

}
