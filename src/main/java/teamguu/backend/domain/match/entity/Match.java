package teamguu.backend.domain.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.EntityDateInfo;
import teamguu.backend.domain.team.entity.Team;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "`match`")
public class Match extends EntityDateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    private String place;
    private String date;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Status status;

    public void changeStatusToComplete() {
        this.status = Status.COMPLETE;
    }
}
