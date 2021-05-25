package backend.nomad.controller;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.member.MemberOrderResponseDto;
import backend.nomad.dto.member.MemberResponseDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreRequestDto;
import backend.nomad.dto.store.StoreResponseDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final MemberService memberService;
    private final MenuService menuService;



    @PostMapping("/store")
    public void saveStore(@RequestBody StoreRequestDto dto, @RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();



        Member member = memberService.findByUid(uid);
        Store store = new Store();
        store.setMember(member);

//        member.getStore().setMember(member);
        store.setStoreName(dto.getStoreName());
        store.setPhoneNumber(dto.getPhoneNumber());
        store.setAddress(dto.getAddress());
        store.setLatitude(dto.getLatitude());
        store.setLongitude(dto.getLongitude());
        store.setOpenTime(dto.getOpenTime());
        store.setCloseTime(dto.getCloseTime());
        store.setDeliveryTip(dto.getDeliveryTip());
        store.setLogoUrl(dto.getLogoUrl());

        storeService.save(store);
        memberService.save(store.getMember());
    }

    @GetMapping("/storeList")
    public Result getAllStoreList() {
        List<Store> store = storeService.findStores();
        List<StoreResponseDto> dtoList = new ArrayList<>();
        for (Store x : store) {
            List<Menu> menu = x.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription()))
                    .collect(Collectors.toList());
            StoreResponseDto dto = new StoreResponseDto(x.getStoreId(), x.getStoreName(), x.getPhoneNumber(), x.getAddress(), x.getLatitude(), x.getLongitude(), x.getOpenTime(), x.getCloseTime(), x.getDeliveryTip(), x.getLogoUrl(), menuList);
            dtoList.add(dto);
        }
        return new Result(dtoList);
    }

    @GetMapping("/myStoreList")
    public Result getStoreList(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();

        List<Menu> menu = store.getMenu();
        List<MenuResponseDto> menuList = menu.stream()
                .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription()))
                .collect(Collectors.toList());

        StoreResponseDto storeResponseDto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList);
        return new Result(storeResponseDto);
//        return new Result(storeResponseDto);
    }
    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class ResultList<T, U> {
        private T storeData;
        private U menuList;
    }
}
