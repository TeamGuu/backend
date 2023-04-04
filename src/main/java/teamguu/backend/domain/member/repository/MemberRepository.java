package teamguu.backend.domain.member.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsernameAndPassword(String username, String password);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByUsername(String username);
    public boolean existsByUsername(String username);
    public boolean existsByNickname(String nickname);

}