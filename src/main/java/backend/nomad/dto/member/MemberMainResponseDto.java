package backend.nomad.dto.member;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class MemberMainResponseDto {
    private long id;

    private String nickName;

    private String email;

    private String phoneNum;

    private LocalDateTime created;

    private String token;

    private long point;

    public MemberMainResponseDto(long id, String nickName, String email, String phoneNum, LocalDateTime created, String token, long point) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.created = created;
        this.token = token;
        this.point = point;
    }
}
