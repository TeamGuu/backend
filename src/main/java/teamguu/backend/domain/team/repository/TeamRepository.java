package teamguu.backend.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.team.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
    List<Team> findTeamsByCaptainId(Long captainId);
}
