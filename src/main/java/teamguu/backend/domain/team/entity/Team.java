package teamguu.backend.domain.team.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.EntityDateInfo;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.reservationinfo.entity.ReservationInfo;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Team extends EntityDateInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member captain;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationInfo> reservationInfos = new ArrayList<>();

    private String name;

    @Enumerated(EnumType.STRING)
    private Sports sports;

    private String history;

    private int ageAvg;

    private String intro;

    private String logoImageUrl;

    private String playerInfo;

    private int victory;
    private int draw;
    private int defeat;
}
