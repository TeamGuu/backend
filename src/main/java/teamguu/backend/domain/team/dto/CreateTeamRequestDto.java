package teamguu.backend.domain.team.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.team.entity.Sports;
import teamguu.backend.domain.team.entity.Team;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamRequestDto {
    @NotBlank(message = "팀 이름을 입력해주세요.")
    @Schema(description = "팀 이름", defaultValue = "명지 FC")
    private String name;
    @NotBlank(message = "팀의 스포츠 종목을 입력해주세요.")
    @Schema(description = "팀 스포츠 종목", defaultValue = "축구")
    private String sports;
    @Schema(description = "팀 약력.", defaultValue = "용인시장 배 토너먼트 2회 우승")
    private String history;

    @Schema(description = "팀의 한줄소개.", defaultValue = "최고의 팀 명지 FC 입니다.")
    private String intro;
    @NotBlank(message = "팀의 멤버정보를 입력해주세요.")
    @Schema(description = "팀 멤버정보.", defaultValue = "FW 최재희 (25세 남), MF 위진영 (25세 남), MF 문민우 (25세 남), DF 전인재 (17세 여), DF 지소영 (24세 여), GK 김상 (52세 남)")
    private String playerInfo;

    public Team toEntity(Member captain) {

        return Team.builder()
                .name(this.name)
                .captain(captain)
                .sports(Sports.nameOf(this.sports))
                .history(this.history)
                .intro(this.intro)
                .playerInfo(this.playerInfo)
                .logoImageUrl("nothing")
                .defeat(0)
                .draw(0)
                .victory(0)
                .ageAvg(0)
                .build();
    }
}