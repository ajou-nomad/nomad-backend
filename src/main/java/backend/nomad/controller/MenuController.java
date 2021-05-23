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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MemberService memberService;
    private final StoreService storeService;

    private final Logger logger;

    @PostMapping("/menu")
    public void savaStoreMenu(@RequestBody MenuRequestDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        System.out.println("dto 값" + dto);

        Member member = memberService.findByUid(uid);
        Store store = member.getStore().get(0);
        Menu menu = new Menu();
        menu.addMenu(store);
        menu.setMenuName(dto.getMenuName());
        menu.setCost(dto.getCost());
        menu.setDescription(dto.getDescription());
        menu.setImgUrl(dto.getImgUrl());
//        Member member = memberService.findByUid(uid);
        menuService.save(menu);
        storeService.save(store);
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
