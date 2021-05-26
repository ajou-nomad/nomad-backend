package backend.nomad.dto.group;

import backend.nomad.dto.store.MenuResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GroupOrderResponseDto {

    private Long groupId;
    private List<MenuResponseDto> orderItem = new ArrayList<>();
    private String building;
//    private
}
