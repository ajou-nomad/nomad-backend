package backend.nomad.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponseDto {
    private Long reviewId;
    private String nickName;
    private String contents;
    private String uid;
    private String imgUrl;
    private Double rate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime localDateTime;

    public ReviewResponseDto(Long reviewId, String nickName, String contents, String imgUrl, Double rate, LocalDateTime localDateTime) {
        this.reviewId = reviewId;
        this.nickName = nickName;
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.rate = rate;
        this.localDateTime = localDateTime;
    }

    public ReviewResponseDto() {

    }
}
