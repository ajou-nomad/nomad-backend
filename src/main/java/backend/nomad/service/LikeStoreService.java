package backend.nomad.service;

import backend.nomad.domain.likestore.LikeStore;
import backend.nomad.domain.likestore.LikeStoreRepository;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeStoreService {

    private final LikeStoreRepository likeStoreRepository;

    @Transactional
    public Long save(LikeStore likeStore) {
        return likeStoreRepository.save(likeStore).getLikeStoreId();
    }

    @Transactional
    public List<LikeStore> findAllMenu() {
        return likeStoreRepository.findAll();
    }

    @Transactional
    public List<LikeStore> findByUid(String uid) {
        return likeStoreRepository.findByUid(uid);
    }

    @Transactional
    public LikeStore findByUidAndStoreId(String uid, Long storeId) {
        return likeStoreRepository.findByUidAndStoreId(uid, storeId);
    }
    @Transactional
    public void delete(LikeStore likeStore) {
        likeStoreRepository.delete(likeStore);
    }
}
