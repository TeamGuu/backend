package practice.weakpoint.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.weakpoint.domain.member.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}