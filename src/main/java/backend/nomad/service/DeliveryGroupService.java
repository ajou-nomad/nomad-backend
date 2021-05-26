package backend.nomad.service;


import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.DeliveryGroupRepository;
import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryGroupService {

    private final DeliveryGroupRepository deliveryGroupRepository;

    @Transactional
    public Long save(DeliveryGroup deliveryGroup) {
        return deliveryGroupRepository.save(deliveryGroup).getGroupId();
    }

    @Transactional
    public List<DeliveryGroup> findGroups() {
        return deliveryGroupRepository.findAll();
    }

    @Transactional
    public DeliveryGroup findById(Long id) {
        return deliveryGroupRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<DeliveryGroup> findByGroupTypeAndOrderStatus(GroupType groupType, OrderStatus orderStatus) {
        return deliveryGroupRepository.findByGroupTypeAndOrderStatus(groupType, orderStatus);
    }

    @Transactional
    public List<DeliveryGroup> findByStoreId(Long storeId) {
        return deliveryGroupRepository.findByStoreId(storeId);
    }

    @Transactional
    public List<DeliveryGroup> findByOrderStatus(OrderStatus orderStatus) {
        return deliveryGroupRepository.findByOrderStatus(orderStatus);
    }
    @Transactional
    public List<DeliveryGroup> findByOrderStatusAndStoreId(OrderStatus orderStatus, Long storeId) {
        return deliveryGroupRepository.findByOrderStatusAndStoreId(orderStatus,storeId);
    }

}
