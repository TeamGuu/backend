package teamguu.backend.domain.match.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.match.entity.Match;
import teamguu.backend.domain.member.entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetChatMemberInfoResponseDto {

    private String receiverUsername;
    private String senderUsername;

    public static GetChatMemberInfoResponseDto from(Match match, Member sender) {
        return GetChatMemberInfoResponseDto.builder()
                .receiverUsername(match.getTeam().getCaptain().getUsername())
                .senderUsername(sender.getUsername())
                .build();
    }
}
