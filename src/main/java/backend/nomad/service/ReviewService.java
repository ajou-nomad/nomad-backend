package backend.nomad.service;

import backend.nomad.domain.member.MemberOrder;
import backend.nomad.domain.review.Review;
import backend.nomad.domain.review.ReviewRepository;
import backend.nomad.domain.store.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Long save(Review review) {
        return reviewRepository.save(review).getReviewId();
    }

    @Transactional
    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }

    @Transactional
    public List<Review> findByUid(String uid) {
        return reviewRepository.findByUid(uid);
    }

    @Transactional
    public Review findByReviewId(Long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    @Transactional
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
