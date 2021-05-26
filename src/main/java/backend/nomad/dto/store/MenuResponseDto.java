package backend.nomad.dto.store;

import backend.nomad.domain.orderitem.OrderItem;
import backend.nomad.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuResponseDto {

    private Long menuId;
    private String menuName;
    private Integer cost;
    private String description;
//    detail?
    private String imgUrl;

    public MenuResponseDto(Long menuId, String menuName, Integer cost, String description) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.cost = cost;
        this.description = description;
    }

    public MenuResponseDto(Long menuId, String menuName, Integer cost, String description, String imgUrl) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.cost = cost;
        this.description = description;
        this.imgUrl = imgUrl;
    }
}
