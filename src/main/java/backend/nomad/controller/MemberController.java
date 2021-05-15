package backend.nomad.controller;


import backend.nomad.domain.member.Member;
import backend.nomad.dto.member.MemberMainResponseDto;
import backend.nomad.dto.member.MemberSaveRequestDto;
import backend.nomad.service.MemberService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public Long saveMember(@RequestBody MemberSaveRequestDto dto) {
        return memberService.save(dto);
    }

    @GetMapping("/member")
    public int authUser(@RequestHeader("Authorization") String header) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(header);
        String uid = decodedToken.getUid();

        List<Member> findMembers = memberService.findMembers();
        List<MemberMainResponseDto> collect = findMembers.stream()
                .map(m -> new MemberMainResponseDto(m.getMemberId(), m.getNickName(), m.getEmail(), m.getPhoneNum(), m.getToken(), m.getUid(), m.getMemberType()))
                .collect(Collectors.toList());
        for (Member member : findMembers) {
            System.out.println(member.getUid());
            if (member.getUid().equals(uid)) {
                return 1;
            }
        }
        return 0;
    }


    @GetMapping("/memberList")
    public Result findMembers() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberMainResponseDto> collect = findMembers.stream()
                .map(m -> new MemberMainResponseDto(m.getMemberId(), m.getNickName(), m.getEmail(), m.getPhoneNum(), m.getToken(), m.getUid(), m.getMemberType()))
                .collect(Collectors.toList());

        return new Result(collect);
    }
    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

}
