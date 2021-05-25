package backend.nomad.dto.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
    private Long reviewId;
    private String contents;
    private String uid;
    private String imgUrl;
    private Double rate;

    public ReviewResponseDto(Long reviewId, String contents, String imgUrl, Double rate) {
        this.reviewId = reviewId;
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.rate = rate;
    }
}
