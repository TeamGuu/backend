package teamguu.backend.domain.member.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String username);

    public boolean existsByEmail(String username);
}