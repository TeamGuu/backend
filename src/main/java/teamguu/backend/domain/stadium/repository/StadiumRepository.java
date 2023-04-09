package teamguu.backend.domain.stadium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.stadium.entity.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}
