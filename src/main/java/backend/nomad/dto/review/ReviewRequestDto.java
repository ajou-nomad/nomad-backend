package backend.nomad.dto.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private String contents;
    private Double rate;
    private String imgUrl;
}
