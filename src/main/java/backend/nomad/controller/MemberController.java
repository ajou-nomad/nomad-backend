package backend.nomad.controller;


import backend.nomad.dto.member.MemberSaveRequestDto;
import backend.nomad.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public Long saveMember(@RequestBody MemberSaveRequestDto dto) {
        return memberService.save(dto);
    }
}
