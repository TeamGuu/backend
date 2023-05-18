package teamguu.backend.domain.matchinginfo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.EntityDateInfo;
import teamguu.backend.domain.team.entity.Sports;
import teamguu.backend.domain.team.entity.Team;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class MatchingInfo extends EntityDateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchingInfo_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    // TODO 생각해보니 MatchingInfo 에 sports 컬럼이 있을 필요가 있나?! team 으로 알 수 있지 않나?
    @Enumerated(EnumType.STRING)
    private Sports sports;
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
