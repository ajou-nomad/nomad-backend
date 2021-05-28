package backend.nomad.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberOrderRepository extends JpaRepository<MemberOrder, Long> {

    MemberOrder findByStoreId(Long storeId);
    List<MemberOrder> findByStoreIdAndUid(Long storeId, String uid);
}
