package teamguu.backend.domain.team.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.team.entity.Team;

//TODO Simple Response 에선 어떤 정보가 있어야 할까
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleTeamInfoResponseDto {

    @Schema(description = "팀 ID")
    private Long id;
    @Schema(description = "팀 이름")
    private String name;
    @Schema(description = "팀 스포츠 종목")
    private String sports;
    @Schema(description = "팀 주장이름")
    private String captain;
    @Schema(description = "팀의 한줄소개")
    private String intro;
    @Schema(description = "팀 로고 이미지 URL")
    private String logoImageUrl;

    public static SimpleTeamInfoResponseDto from(Team team) {
        return SimpleTeamInfoResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .captain(team.getCaptain().getName())
                .sports(team.getSports().getName())
                .intro(team.getIntro())
                .logoImageUrl(team.getLogoImageUrl())
                .build();
    }
}
