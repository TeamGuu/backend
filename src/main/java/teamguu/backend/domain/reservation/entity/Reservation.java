package teamguu.backend.domain.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.EntityDateInfo;
import teamguu.backend.domain.stadium.entity.Stadium;
import teamguu.backend.domain.team.entity.Team;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Reservation extends EntityDateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
    private String date;
}
