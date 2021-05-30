package backend.nomad.controller;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.store.MenuRequestDto;
import backend.nomad.service.MemberService;
import backend.nomad.service.MenuService;
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
//        storeService.save(member.getStore());
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
