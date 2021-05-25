package backend.nomad.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private String contents;
    private Double rate;
    private String imgUrl;
}
