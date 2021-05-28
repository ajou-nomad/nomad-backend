package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.OrderStatus;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.dto.group.GroupOrderResponseDto;
import backend.nomad.service.DeliveryGroupService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DeliveryManController {

    private final DeliveryGroupService deliveryGroupService;

    @GetMapping("/deliveringGroupData")
    public Result getDeliveryGroupData() {
        List<DeliveryGroup> deliveryGroup = deliveryGroupService.findByOrderStatus(OrderStatus.waitingForDelivery);

        List<DeliveryGroupResponseDto> collect = deliveryGroup.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuildingName(), m.getDeliveryDateTime(), m.getCurrent(),  m.getMaxValue(), m.getGroupType(), m.getOrderStatus()))
                .collect(Collectors.toList());

        return new Result(deliveryGroup);
    }

    @PostMapping("/deliveringGroupData")
    public void setDeliveryGroupData(@RequestBody DeliveryGroupRequestDto deliveryGroupRequestDto) {
        DeliveryGroup deliveryGroup = deliveryGroupService.findById(deliveryGroupRequestDto.getGroupId());
        deliveryGroup.setOrderStatus(OrderStatus.delivering);
        deliveryGroupService.save(deliveryGroup);
    }

    @PostMapping("/deliveryComplete")
    public void deliveryComplete(@RequestBody DeliveryGroupRequestDto deliveryGroupRequestDto) {
        DeliveryGroup deliveryGroup = deliveryGroupService.findById(deliveryGroupRequestDto.getGroupId());
        deliveryGroup.setOrderStatus(OrderStatus.deliveryDone);
        deliveryGroupService.save(deliveryGroup);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }
}
