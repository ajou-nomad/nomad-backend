//package backend.nomad.service;
//
//import backend.nomad.domain.store.Store;
//import backend.nomad.domain.store.StoreRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class StoreService {
//
//    private final StoreRepository storeRepository;
//
//    @Transactional
//    public Long save(Store store) {
//        return storeRepository.save(store).getStoreId();
//    }
//
//    @Transactional
//    public List<Store> findMembers() {
//        return storeRepository.findAll();
//    }
//}
