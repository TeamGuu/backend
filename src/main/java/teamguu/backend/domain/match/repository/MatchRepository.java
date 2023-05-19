package teamguu.backend.domain.match.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.match.entity.Match;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @EntityGraph(attributePaths = {"team"})
    @NotNull
    Page<Match> findAll(@NotNull Pageable pageable);
}
