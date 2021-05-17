package backend.nomad.service;


import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.DeliveryGroupRepository;
//import backend.nomad.domain.group.uid;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
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
    public Optional<DeliveryGroup> findById(Long id) {
        return deliveryGroupRepository.findById(id);
    }
}
