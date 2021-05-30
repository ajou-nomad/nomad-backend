package backend.nomad.domain.store;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PromotionMenu {

    @Id
    @GeneratedValue
    @Column(name = "promotionMenu_id")
    private Long promotionMenuId;

    private String promotionMenuName;
    private Integer cost;
    private String description;
    private String promotionDescription;
    private String imgUrl;

    @OneToOne(fetch = FetchType.LAZY)
    private Store store;

    public PromotionMenu() {

    }
}
