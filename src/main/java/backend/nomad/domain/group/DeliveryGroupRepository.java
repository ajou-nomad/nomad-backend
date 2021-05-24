package backend.nomad.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryGroupRepository extends JpaRepository<DeliveryGroup, Long> {

    List<DeliveryGroup> findByGroupType(GroupType groupType);

}
