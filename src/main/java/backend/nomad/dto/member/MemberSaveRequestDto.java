package backend.nomad.dto.member;

import backend.nomad.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String nickName;

    private String email;

    private String phoneNum;

    private String token;

    private long point;

    @Builder
    public MemberSaveRequestDto(String nickName, String email, String phoneNum, String token, long point) {
        this.nickName = nickName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.token = token;
        this.point = point;
    }

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .phoneNum(phoneNum)
                .token(token)
                .point(point)
                .build();
    }
}
