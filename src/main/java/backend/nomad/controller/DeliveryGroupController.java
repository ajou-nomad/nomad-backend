package backend.nomad.controller;

import backend.nomad.dto.group.DeliveryGroupSaveRequestDto;
import backend.nomad.service.deliveryGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryGroupController {

    public final deliveryGroupService deliveryGroupService;

    @PostMapping("/group")
    public Long SaveGroup(@RequestBody DeliveryGroupSaveRequestDto dto) {
        return deliveryGroupService.save(dto);
    }
}
