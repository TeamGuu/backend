package teamguu.backend.domain.match.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.match.entity.Match;
import teamguu.backend.domain.match.entity.Status;
import teamguu.backend.domain.team.entity.Team;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchRequestDto {

    @NotBlank(message = "매칭 희망 장소를 입력해주세요.")
    @Schema(description = "매칭 희망 장소", defaultValue = "경기도 용인시")
    private String place;

    @NotBlank(message = "매칭 희망 일시를 입력해주세요.")
    @Schema(description = "매칭 희망 일시", defaultValue = "2023-04-19")
    private String date;

    @NotBlank(message = "매칭 공고문 제목을 입력해주세요.")
    @Schema(description = "매칭 공고문 제목", defaultValue = "안녕하세요! 명지FC 입니다. 대결하실 분~?")
    private String title;

    @Schema(description = "매칭 공고문 내용", defaultValue = "실력은 중상입니다. 장소는 편하신 곳으로 같이 정해봐요! 채팅 Come on~")
    private String content;

    public Match toEntity(Team team) {
        return Match.builder()
                .team(team)
                .place(this.place)
                .date(this.date)
                .title(this.title)
                .content(this.content)
                .status(Status.WAITED)
                .build();
    }
}
