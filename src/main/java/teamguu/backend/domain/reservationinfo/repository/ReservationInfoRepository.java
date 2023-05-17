package teamguu.backend.domain.reservationinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.reservationinfo.entity.ReservationInfo;
import teamguu.backend.domain.stadium.entity.Stadium;
import teamguu.backend.domain.team.entity.Team;

import java.util.List;
import java.util.Optional;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {

    Optional<ReservationInfo> findByTeam(Team team);
    List<ReservationInfo> findReservationInfosByStadium(Stadium stadium);

}
