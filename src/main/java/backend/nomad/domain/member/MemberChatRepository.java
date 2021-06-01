package backend.nomad.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChatRepository extends JpaRepository<MemberChat, Long> {
}
