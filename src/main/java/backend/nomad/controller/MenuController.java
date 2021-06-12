package backend.nomad.controller;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Promotion;
import backend.nomad.domain.store.PromotionMenu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.store.MenuDeleteDto;
import backend.nomad.dto.store.MenuModifyDto;
import backend.nomad.dto.store.MenuRequestDto;
import backend.nomad.dto.store.PromotionMenuDto;
import backend.nomad.service.MemberService;
import backend.nomad.service.MenuService;
import backend.nomad.service.PromotionMenuService;
import backend.nomad.service.StoreService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MemberService memberService;
    private final StoreService storeService;
    private final PromotionMenuService promotionMenuService;

    @PostMapping("/menu")
    public void savaStoreMenu(@RequestBody MenuRequestDto[] dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();
        for (int i = 0; i < dto.length; i++) {
            Menu menu = new Menu();
            menu.addMenu(store);
            menu.setMenuName(dto[i].getMenuName());
            menu.setCost(dto[i].getCost());
            menu.setDescription(dto[i].getDescription());
            menu.setImgUrl(dto[i].getImgUrl());
//        Member member = memberService.findByUid(uid);
            menuService.save(menu);
            storeService.save(store);
        }
    }

    @PostMapping("/deleteMenu")
    public void deleteStoreMenu(@RequestBody MenuDeleteDto menuDeleteDto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();
        Menu menu = menuService.findById(menuDeleteDto.getMenuId());

        menu.deleteMenu(store);
        storeService.save(store);

        menuService.delete(menu);

    }

    @PostMapping("/modifyMenu")
    public void modifyMenu(@RequestBody MenuModifyDto menuModifyDto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();
        Menu menu = menuService.findById(menuModifyDto.getMenuId());
        menu.setMenuName(menuModifyDto.getMenuName());
        menu.setCost(menuModifyDto.getCost());
        menu.setDescription(menuModifyDto.getDescription());

        menuService.save(menu);
        storeService.save(store);

    }

    @PostMapping("/promotionMenu")
    public void savePromotion(@RequestBody PromotionMenuDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();

        store.setPromotion(Promotion.On);

        PromotionMenu promotionMenu = new PromotionMenu();
        promotionMenu.setPromotionMenuName(dto.getPromotionMenuName());
        promotionMenu.setCost(dto.getCost());
        promotionMenu.setDescription(dto.getDescription());
        promotionMenu.setPromotionDescription(dto.getPromotionDescription());
        promotionMenu.setImgUrl(dto.getImgUrl());
        promotionMenu.setStore(store);

        promotionMenuService.save(promotionMenu);
        storeService.save(store);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class ResultList<T, U> {
        private T dataT;
        private U dataU;
    }
}
