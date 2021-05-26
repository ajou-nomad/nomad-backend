package backend.nomad.dto.group;

import backend.nomad.domain.group.GroupType;
import backend.nomad.domain.store.Menu;
import backend.nomad.dto.store.MenuRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryGroupRequestDto {
    private Long groupId;
    private Long storeId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String buildingName;
    private String time;
    private String date;
    private Integer current;
    private Integer maxValue;
    private GroupType groupType;
    private List<MenuRequestDto> menu;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime localDateTime;
//    private String menuName;
//    private Integer cost;
//    private Integer quantity;
    private Integer totalCost;
    private String payMethod;
    private String orderTime;
}
