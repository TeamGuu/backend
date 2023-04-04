package teamguu.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.member.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}