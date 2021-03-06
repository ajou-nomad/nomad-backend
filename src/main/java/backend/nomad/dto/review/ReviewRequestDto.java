package backend.nomad.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private Long reviewId;
    private String contents;
    private Double rate;
    private String imgUrl;
    private Long storeId;
    private Long memberOrderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime localDateTime;
}
