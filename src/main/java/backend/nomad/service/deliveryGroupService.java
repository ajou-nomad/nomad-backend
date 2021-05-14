package backend.nomad.service;


import backend.nomad.domain.group.DeliveryGroupRepository;
import backend.nomad.dto.group.DeliveryGroupSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class deliveryGroupService {

    private final DeliveryGroupRepository deliveryGroupRepository;

    @Transactional
    public Long save(DeliveryGroupSaveRequestDto dto) {
        return deliveryGroupRepository.save(dto.toEntity()).getGroupId();
    }
}
