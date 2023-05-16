package teamguu.backend.domain.stadium.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.stadium.entity.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    @NotNull
    Page<Stadium> findAll(@NotNull Pageable pageable);
}
