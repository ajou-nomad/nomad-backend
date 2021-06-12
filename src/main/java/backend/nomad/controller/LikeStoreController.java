package backend.nomad.controller;

import backend.nomad.domain.likestore.LikeStore;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Menu;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.dto.store.MenuResponseDto;
import backend.nomad.dto.store.StoreRequestDto;
import backend.nomad.dto.store.StoreResponseDto;
import backend.nomad.service.LikeStoreService;
import backend.nomad.service.MemberService;
import backend.nomad.service.StoreService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LikeStoreController {

    private final LikeStoreService likeStoreService;
    private final MemberService memberService;
    private final StoreService storeService;

    @PostMapping("/likeStore")
    public void saveLikeStore(@RequestHeader("Authorization") String header, @RequestBody StoreRequestDto storeRequestDto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = storeService.findByStoreId(storeRequestDto.getStoreId());

        LikeStore likeStore = new LikeStore();
        likeStore.setUid(uid);

        likeStore.addLikeToMember(member);
        memberService.save(member);

        likeStore.addLikeToStore(store);
        storeService.save(store);

        likeStoreService.save(likeStore);

    }

    @DeleteMapping("/likeStore")
    public void deleteLikeStore(@RequestHeader("Authorization") String header, @RequestBody StoreRequestDto storeRequestDto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = storeService.findByStoreId(storeRequestDto.getStoreId());

        LikeStore likeStore = new LikeStore();
        likeStore.setUid(uid);

        likeStore.deleteMember(member);
        memberService.save(member);

        likeStore.deleteStore(store);
        storeService.save(store);

        likeStoreService.delete(likeStore);

    }
    @GetMapping("/likeStore")
    public Result likeStoreList(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        List<LikeStore> likeStore = likeStoreService.findByUid(uid);
        List<StoreResponseDto> dtoList = new ArrayList<>();
        for (LikeStore x : likeStore) {
            Store store = x.getStore();

            List<Menu> menu = store.getMenu();
            List<MenuResponseDto> menuList = menu.stream()
                    .map(m -> new MenuResponseDto(m.getMenuId(), m.getMenuName(), m.getCost(), m.getDescription(), m.getImgUrl()))
                    .collect(Collectors.toList());

            List<Review> review = store.getReview();
            List<ReviewResponseDto> reviewList = review.stream()
                    .map(m -> new ReviewResponseDto(m.getReviewId(), null, null, m.getNickName(), m.getContents(), m.getImgUrl(), m.getRate(), m.getLocalDateTime()))
                    .collect(Collectors.toList());

            StoreResponseDto dto = new StoreResponseDto(store.getStoreId(), store.getStoreName(), store.getPhoneNumber(), store.getAddress(), store.getLatitude(), store.getLongitude(), store.getOpenTime(), store.getCloseTime(), store.getDeliveryTip(), store.getLogoUrl(), menuList, reviewList, store.getRate());

            dtoList.add(dto);
        }

        return new Result(dtoList);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }
}
