package backend.nomad.controller;

import backend.nomad.domain.group.DeliveryGroup;
//import backend.nomad.domain.group.uid;
import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberRepository;
import backend.nomad.dto.group.DeliveryGroupResponseDto;
import backend.nomad.dto.group.DeliveryGroupRequestDto;
import backend.nomad.service.DeliveryGroupService;
import backend.nomad.service.MemberService;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeliveryGroupController {

    private Logger logger;
    private final DeliveryGroupService deliveryGroupService;
    private final MemberService memberService;
    @PostMapping("/groupData")
    public Long SaveGroup(@RequestBody DeliveryGroupRequestDto dto) throws FirebaseAuthException {
        String uid = "asd";
        DeliveryGroup deliveryGroup = new DeliveryGroup();
        deliveryGroup.setStoreId(dto.getStoreId());
        deliveryGroup.setLatitude(dto.getLatitude());
        deliveryGroup.setLongitude(dto.getLongitude());
        deliveryGroup.setAddress(dto.getAddress());
        deliveryGroup.setBuilding(dto.getBuilding());
        deliveryGroup.setTime(dto.getTime());
        deliveryGroup.setDate(dto.getDate());
        deliveryGroup.setCurrent(1);
        deliveryGroup.setMaxValue(dto.getMaxValue());
        deliveryGroup.setGroupType(dto.getGroupType());

        Member member = memberService.findByUid(uid);
        member.setDeliveryGroup(deliveryGroup);
        member.changeGroup(deliveryGroup);
        memberService.save(member);

        return deliveryGroupService.save(deliveryGroup);
    }

    @PostMapping("/participationGroup")
    public Result addInGroup(@RequestBody DeliveryGroupRequestDto dto) throws FirebaseAuthException {
        String uid = "random";
        Member member = memberService.findByUid(uid);
        Optional<DeliveryGroup> deliveryGroup = deliveryGroupService.findById(dto.getGroupId());
        member.setDeliveryGroup(deliveryGroup.get());
        member.changeGroup(deliveryGroup.get());
        memberService.save(member);
        deliveryGroupService.save(deliveryGroup.get());

        int current = deliveryGroup.get().getCurrent();
        int maxValue = deliveryGroup.get().getMaxValue();
        if (current == maxValue) {
            return new Result("MAX");
        }


        return new Result(HttpStatus.ACCEPTED);
    }

    @GetMapping("/groupList")
    public Result findGroups() {
        List<DeliveryGroup> findGroups = deliveryGroupService.findGroups();
        List<DeliveryGroupResponseDto> collect = findGroups.stream()
                .map(m -> new DeliveryGroupResponseDto(m.getGroupId(), m.getStoreId(), m.getLatitude(), m.getLongitude(), m.getAddress(), m.getBuilding(), m.getTime(), m.getDate(),m.getCurrent(),  m.getMaxValue(),m.getGroupType()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class ResultList<T, U> {
        private T dataT;
        private U dataU;
    }
}
