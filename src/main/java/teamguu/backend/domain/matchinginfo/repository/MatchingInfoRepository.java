package teamguu.backend.domain.matchinginfo.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.matchinginfo.entity.MatchingInfo;

public interface MatchingInfoRepository extends JpaRepository<MatchingInfo, Long> {
    @NotNull
    Page<MatchingInfo> findAll(@NotNull Pageable pageable);
}
