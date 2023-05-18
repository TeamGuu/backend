package teamguu.backend.domain.match.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.match.entity.Match;
import teamguu.backend.domain.team.dto.SimpleTeamInfoResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleMatchInfoResponseDto {

    private Long id;
    private SimpleTeamInfoResponseDto simpleTeamInfo;
    private String place;
    private String date;
    private String title;
    private String status;

    public static SimpleMatchInfoResponseDto from(Match match) {

        SimpleTeamInfoResponseDto team = SimpleTeamInfoResponseDto.from(match.getTeam());

        return SimpleMatchInfoResponseDto.builder()
                .id(match.getId())
                .simpleTeamInfo(team)
                .place(match.getPlace())
                .date(match.getDate())
                .title(match.getTitle())
                .status(match.getStatus().getName())
                .build();
    }
}
