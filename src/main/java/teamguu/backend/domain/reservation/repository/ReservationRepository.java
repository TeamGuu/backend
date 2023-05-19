package teamguu.backend.domain.reservation.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import teamguu.backend.domain.reservation.entity.Reservation;
import teamguu.backend.domain.stadium.entity.Stadium;
import teamguu.backend.domain.team.entity.Team;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTeam(Team team);
    @EntityGraph(attributePaths = {"team", "stadium"})
    List<Reservation> findReservationsByStadium(Stadium stadium);
}
