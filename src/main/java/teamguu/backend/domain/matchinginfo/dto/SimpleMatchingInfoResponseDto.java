package teamguu.backend.domain.matchinginfo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.matchinginfo.entity.MatchingInfo;
import teamguu.backend.domain.team.dto.SimpleTeamInfoResponseDto;
import teamguu.backend.domain.team.entity.Team;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleMatchingInfoResponseDto {

    @Schema(description = "매칭 공고문 ID")
    private Long id;
    @Schema(description = "간단한 팀 정보")
    private SimpleTeamInfoResponseDto simpleTeamInfo;
    @Schema(description = "매칭 희망 지역")
    private String place;
    @Schema(description = "매칭 희망 일시")
    private String date;
    @Schema(description = "매칭 공고 제목")
    private String title;
    @Schema(description = "매칭 공고 상태")
    private String status;

    public static SimpleMatchingInfoResponseDto from(MatchingInfo matchingInfo) {

        SimpleTeamInfoResponseDto team = SimpleTeamInfoResponseDto.from(matchingInfo.getTeam());

        return SimpleMatchingInfoResponseDto.builder()
                .id(matchingInfo.getId())
                .simpleTeamInfo(team)
                .place(matchingInfo.getPlace())
                .date(matchingInfo.getDate())
                .title(matchingInfo.getTitle())
                .status(matchingInfo.getStatus().getName())
                .build();
    }
}
