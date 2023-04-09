package teamguu.backend.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.team.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
