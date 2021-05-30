package backend.nomad.dto.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionMenuDto {
    private String promotionMenuName;
    private Integer cost;
    private Integer quantity;
    private String description;
    private String promotionDescription;
    private String imgUrl;

}