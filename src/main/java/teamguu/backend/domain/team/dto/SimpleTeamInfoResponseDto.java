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
public class SimpleTeamInfoResponseDto {

    private Long id;
    private String name;
    private String sports;
    private String logoImageUrl;
    private int victory;
    private int draw;
    private int defeat;

    public static SimpleTeamInfoResponseDto from(Team team) {
        return SimpleTeamInfoResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .sports(team.getSports().getName())
                .logoImageUrl(team.getLogoImageUrl())
                .victory(team.getVictory())
                .draw(team.getDraw())
                .defeat(team.getDefeat())
                .build();
    }
}
