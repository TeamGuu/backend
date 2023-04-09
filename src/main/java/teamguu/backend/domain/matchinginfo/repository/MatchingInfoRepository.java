package teamguu.backend.domain.matchinginfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.matchinginfo.entity.MatchingInfo;

public interface MatchingInfoRepository extends JpaRepository<MatchingInfo, Long> {
}
