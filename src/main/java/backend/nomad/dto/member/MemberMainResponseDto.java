package backend.nomad.dto.member;

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

    public MemberMainResponseDto(Long memberId, String nickName, String email, String phoneNum, String token) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.token = token;
    }
}
