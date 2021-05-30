package backend.nomad.service;

import backend.nomad.domain.store.PromotionMenu;
import backend.nomad.domain.store.PromotionMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionMenuService {

    private final PromotionMenuRepository promotionMenuRepository;

    @Transactional
    public Long save(PromotionMenu promotionMenu) {
        return promotionMenuRepository.save(promotionMenu).getPromotionMenuId();
    }
}
