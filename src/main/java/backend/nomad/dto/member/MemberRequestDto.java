package backend.nomad.dto.member;

import backend.nomad.domain.member.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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




    private Double point;

}
