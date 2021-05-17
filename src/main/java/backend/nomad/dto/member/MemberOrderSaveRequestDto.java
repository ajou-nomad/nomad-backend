//package backend.nomad.dto.member;
//
//import backend.nomad.domain.member.Member;
//import backend.nomad.domain.member.MemberOrder;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import java.util.ArrayList;
//import java.util.List;
//
////@Getter
//@Setter
//public class MemberOrderSaveRequestDto {
//    private Member member;
//
//    @Builder
//    public MemberOrderSaveRequestDto(Member member) {
//        this.member = member;
//    }
//
//    public MemberOrder toEntity() {
//        return MemberOrder.builder()
//                .member(member)
//                .build();
//    }
//}
