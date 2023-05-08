package teamguu.backend.domain.team.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.team.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
    @EntityGraph(attributePaths = {"captain"})
    List<Team> findTeamsByCaptain(Member captain);
}
