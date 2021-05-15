package backend.nomad.dto.member;

import backend.nomad.domain.member.MemberType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class MemberMainResponseDto {
    private Long memberId;
    private String nickName;
    private String email;
    private String phoneNum;
    private String token;
    private String uid;
    private MemberType memberType;

    public MemberMainResponseDto(Long memberId, String nickName, String email, String phoneNum, String token, String uid, MemberType memberType) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.token = token;
        this.uid = uid;
        this.memberType = memberType;
    }
}
