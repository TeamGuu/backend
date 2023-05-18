package teamguu.backend.domain.matchinginfo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.matchinginfo.entity.MatchingInfo;
import teamguu.backend.domain.team.dto.TeamInfoResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchingInfoResponseDto {

    private Long id;
    private TeamInfoResponseDto teamInfo;
    private String date;
    private String place;
    private String title;
    private String content;

    public static MatchingInfoResponseDto from(MatchingInfo matchingInfo) {

        TeamInfoResponseDto team = TeamInfoResponseDto.from(matchingInfo.getTeam());

        return MatchingInfoResponseDto.builder()
                .id(matchingInfo.getId())
                .teamInfo(team)
                .date(matchingInfo.getDate())
                .place(matchingInfo.getPlace())
                .title(matchingInfo.getTitle())
                .content(matchingInfo.getContent())
                .build();
    }
}
