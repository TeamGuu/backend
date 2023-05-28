package teamguu.backend.domain.member.dto.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.member.entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleCurrentMemberInfoResponseDto {
    private Long id;

    public static SimpleCurrentMemberInfoResponseDto from(Member member) {
        return SimpleCurrentMemberInfoResponseDto.builder()
                .id(member.getId())
                .build();
    }
}
