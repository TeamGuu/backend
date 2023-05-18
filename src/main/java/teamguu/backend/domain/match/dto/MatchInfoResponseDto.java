package teamguu.backend.domain.match.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.match.entity.Match;
import teamguu.backend.domain.team.dto.TeamInfoResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchInfoResponseDto {

    private Long id;
    private TeamInfoResponseDto teamInfo;
    private String date;
    private String place;
    private String title;
    private String content;

    public static MatchInfoResponseDto from(Match match) {

        TeamInfoResponseDto team = TeamInfoResponseDto.from(match.getTeam());

        return MatchInfoResponseDto.builder()
                .id(match.getId())
                .teamInfo(team)
                .date(match.getDate())
                .place(match.getPlace())
                .title(match.getTitle())
                .content(match.getContent())
                .build();
    }
}
