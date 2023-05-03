package teamguu.backend.domain.team.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "팀 ID")
    private Long id;
    @Schema(description = "팀 이름")
    private String name;
    @Schema(description = "팀 스포츠 종목")
    private String sports;
    @Schema(description = "팀 주장이름")
    private String captain;
    @Schema(description = "팀 약력")
    private String history;
    @Schema(description = "팀의 한줄소개")
    private String intro;
    @Schema(description = "팀 멤버정보")
    private String playerInfo;
    @Schema(description = "팀 로고 이미지 URL")
    private String logoImageUrl;
    @Schema(description = "팀 승리전적")
    private int victory;
    @Schema(description = "팀 무승부전적")
    private int draw;
    @Schema(description = "팀 패배전적")
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
