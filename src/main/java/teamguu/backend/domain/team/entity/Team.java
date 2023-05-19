package teamguu.backend.domain.team.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.EntityDateInfo;
import teamguu.backend.domain.match.entity.Match;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.reservation.entity.Reservation;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
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
    @OneToMany(mappedBy = "team", cascade = ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
    @OneToMany(mappedBy = "team", cascade = ALL, orphanRemoval = true)
    private List<Match> matches = new ArrayList<>();

    private String name;
    @Enumerated(EnumType.STRING)
    private Sports sports;
    private String history;
    private String intro;
    private String logoImageUrl;
    private String playerInfo;
    private int victory;
    private int draw;
    private int defeat;

    public String changeLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
        return this.logoImageUrl;
    }

    public void editTeam(String name, String history, String intro, String playerInfo) {
        this.name = name;
        this.history = history;
        this.intro = intro;
        this.playerInfo = playerInfo;
    }
}
