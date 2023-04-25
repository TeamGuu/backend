package teamguu.backend.domain.matchinginfo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.matchinginfo.entity.MatchingInfo;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.team.dto.TeamInfoResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchingInfoResponseDto {

    @Schema(description = "매칭 공고문 ID")
    private Long id;
    @Schema(description = "팀 정보")
    private TeamInfoResponseDto teamInfo;
    @Schema(description = "매칭 희망 일시")
    private String date;
    @Schema(description = "매칭 희망 장소")
    private String place;
    @Schema(description = "매칭 공고문 제목")
    private String title;
    @Schema(description = "매칭 공고문 내용")
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
