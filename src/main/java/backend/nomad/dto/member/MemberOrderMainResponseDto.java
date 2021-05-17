package backend.nomad.dto.member;

import backend.nomad.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberOrderMainResponseDto {

    private Long memberOrderId;
    private Member member;

    public MemberOrderMainResponseDto(Long memberOrderId, Member member) {
        this.memberOrderId = memberOrderId;
        this.member = member;
    }
}
