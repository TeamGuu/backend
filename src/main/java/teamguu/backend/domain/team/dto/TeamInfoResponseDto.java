package teamguu.backend.domain.team.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.team.entity.Team;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamInfoResponseDto {

    private Long id;
    private String name;
    private String sports;
    private String captain;
    private String history;
    private String intro;
    private String playerInfo;
    private String logoImageUrl;
    private int victory;
    private int draw;
    private int defeat;

    public static TeamInfoResponseDto from(Team team) {
        return TeamInfoResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .captain(team.getCaptain().getName())
                .sports(team.getSports().getName())
                .history(team.getHistory())
                .intro(team.getIntro())
                .playerInfo(team.getPlayerInfo())
                .logoImageUrl(team.getLogoImageUrl())
                .victory(team.getVictory())
                .draw(team.getDraw())
                .defeat(team.getDefeat())
                .build();
    }
}
