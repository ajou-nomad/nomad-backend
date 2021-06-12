package backend.nomad.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuModifyDto {

    private Long menuId;
    private String menuName;
    private Double cost;
    private String description;

}
