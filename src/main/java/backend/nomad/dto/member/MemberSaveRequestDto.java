package backend.nomad.dto.member;

import backend.nomad.domain.member.Member;
import backend.nomad.domain.member.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
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
    private String uid;
    private MemberType memberType;

    private long point;

    @Builder
    public MemberSaveRequestDto(String nickName, String email, String phoneNum, String token, String uid, MemberType memberType) {
        this.nickName = nickName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.token = token;
        this.uid = uid;
        this.memberType = memberType;
    }

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .phoneNum(phoneNum)
                .token(token)
                .uid(uid)
                .memberType(memberType)
                .build();
    }
}
