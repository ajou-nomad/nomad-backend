package backend.nomad.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOrderRepository extends JpaRepository<MemberOrder, Long> {

    MemberOrder findByStoreId(Long storeId);
}
