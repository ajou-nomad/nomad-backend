package backend.nomad.domain.likestore;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeStoreRepository extends JpaRepository<LikeStore, Long> {

    List<LikeStore> findByUid(String uid);
}
