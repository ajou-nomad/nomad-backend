package backend.nomad.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private String contents;
    private String uid;
    private String imgUrl;
    private Double rate;


    private LocalDateTime localDateTime;

    public ReviewResponseDto(Long reviewId, String contents, String imgUrl, Double rate, LocalDateTime localDateTime) {
        this.reviewId = reviewId;
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.rate = rate;
        this.localDateTime = localDateTime;
    }
}
