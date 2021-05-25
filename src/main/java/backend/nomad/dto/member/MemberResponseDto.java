package backend.nomad.dto.member;

import backend.nomad.domain.member.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {
    private Long memberId;
    private String nickName;
    private String email;
    private String phoneNum;
    private String token;
    private String uid;
    private MemberType memberType;
    private Long point;
    private String shopIdNumber;
    private String deliIdNumber;
    private Long storeId;

    public MemberResponseDto(Long memberId, String nickName, String email, String phoneNum, String token, String uid, MemberType memberType, Long point, String shopIdNumber, String deliIdNumber) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.token = token;
        this.uid = uid;
        this.memberType = memberType;
        this.point = point;
        this.shopIdNumber = shopIdNumber;
        this.deliIdNumber = deliIdNumber;
    }
}
