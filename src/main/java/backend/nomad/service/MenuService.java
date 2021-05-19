package backend.nomad.service;

import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional
    public Long save(Menu menu) {
        return menuRepository.save(menu).getMenuId();
    }

    @Transactional
    public List<Menu> findAllMenu() {
        return menuRepository.findAll();
    }

    @Transactional
    public Menu findByMenuName(String menuName) {
        return menuRepository.findByMenuName(menuName);
    }

}