package backend.nomad.dto.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionMenuDto {
    private Long promotionMenuId;
    private String promotionMenuName;
    private Double cost;
    private String description;
    private String promotionDescription;
    private String imgUrl;

    public PromotionMenuDto(Long promotionMenuId, String promotionMenuName, Double cost, String description, String promotionDescription, String imgUrl) {
        this.promotionMenuId = promotionMenuId;
        this.promotionMenuName = promotionMenuName;
        this.cost = cost;
        this.description = description;
        this.promotionDescription = promotionDescription;
        this.imgUrl = imgUrl;
    }
}