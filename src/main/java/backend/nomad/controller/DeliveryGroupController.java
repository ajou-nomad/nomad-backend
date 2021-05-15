package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.member.Member;
import backend.nomad.dto.group.DeliveryGroupMainResponseDto;
import backend.nomad.dto.group.DeliveryGroupSaveRequestDto;
import backend.nomad.dto.member.MemberMainResponseDto;
import backend.nomad.service.deliveryGroupService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DeliveryGroupController {

    public final deliveryGroupService deliveryGroupService;

    @PostMapping("/group")
    public Long SaveGroup(@RequestBody DeliveryGroupSaveRequestDto dto) {
        return deliveryGroupService.save(dto);
    }

    @GetMapping("/GroupList")
    public Result findGroups() {
        List<DeliveryGroup> findGroups = deliveryGroupService.findGroups();
        List<DeliveryGroupMainResponseDto> collect = findGroups.stream()
                .map(m -> new DeliveryGroupMainResponseDto(m.getGroupId(), m.getGroupNum(), m.getLocation(), m.getStore()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }
}
