package teamguu.backend.domain.matchinginfo.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.matchinginfo.entity.MatchingInfo;

public interface MatchingInfoRepository extends JpaRepository<MatchingInfo, Long> {

    @EntityGraph(attributePaths = {"team"})
    @NotNull
    Page<MatchingInfo> findAll(@NotNull Pageable pageable);
}
