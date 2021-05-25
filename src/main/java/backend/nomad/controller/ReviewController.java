package backend.nomad.controller;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.store.Store;
import backend.nomad.dto.member.MemberOrderResponseDto;
import backend.nomad.dto.review.ReviewRequestDto;
import backend.nomad.dto.review.ReviewResponseDto;
import backend.nomad.service.MemberService;
import backend.nomad.service.ReviewService;
import backend.nomad.service.StoreService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberService memberService;
    private final StoreService storeService;

    @PostMapping("/review")
    public void saveReview(@RequestHeader("Authorization") String header, @RequestBody ReviewRequestDto reviewRequestDto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();

        Review review = new Review();
        review.setContents(reviewRequestDto.getContents());
        review.setRate(reviewRequestDto.getRate());
        review.setImgUrl(reviewRequestDto.getImgUrl());

        review.setStore(store);
        storeService.save(store);

        review.setMember(member);
        memberService.save(member);

        reviewService.save(review);
    }

    @GetMapping("/memberReview")
    public Result memberReview(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        List<Review> review = member.getReview();
        List<ReviewResponseDto> collect = review.stream()
                .map(m -> new ReviewResponseDto(m.getReviewId(), m.getContents(), m.getImgUrl(), m.getRate()))
                .collect(Collectors.toList());

        return new Result(collect);

    }

    @GetMapping("/storeReview")
    public Result storeReview(@RequestHeader("Authorization") String header) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        Member member = memberService.findByUid(uid);
        Store store = member.getStore();
        List<Review> review = store.getReview();

        List<ReviewResponseDto> collect = review.stream()
                .map(m -> new ReviewResponseDto(m.getReviewId(), m.getContents(), m.getImgUrl(), m.getRate()))
                .collect(Collectors.toList());
        return new Result(store.getReview());
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }
}
