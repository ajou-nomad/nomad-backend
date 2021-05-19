package backend.nomad.service;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import backend.nomad.domain.store.Store;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Long save(Store store) {
        return storeRepository.save(store).getStoreId();
    }

    @Transactional
    public List<Store> findStores() {
        return storeRepository.findAll();
    }

    @Transactional
    public Store findByStoreId(Long storeId) {
        return storeRepository.findById(storeId).orElse(null);
    }

}