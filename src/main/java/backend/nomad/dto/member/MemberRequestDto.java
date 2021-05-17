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
public class MemberRequestDto {
    private String nickName;
    private String email;
    private String phoneNum;
    private String token;
    private String uid;
    private MemberType memberType;
    private String shopIdNumber;
    private String deliIdNumber;


    private long point;

}
