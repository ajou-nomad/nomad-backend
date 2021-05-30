package backend.nomad.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuRequestDto {
    private String promotionMenuName;
    private Integer cost;
    private Integer quantity;
    private String description;
    private String promotionDescription;
    private String imgUrl;

}
