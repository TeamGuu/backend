package teamguu.backend.domain.reservationinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.reservationinfo.entity.ReservationInfo;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {
}
