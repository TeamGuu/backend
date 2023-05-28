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
public class SignUpResponseDto {
    private Long id;

    public static SignUpResponseDto from(Member member) {
        return SignUpResponseDto.builder()
                .id(member.getId())
                .build();
    }
}
