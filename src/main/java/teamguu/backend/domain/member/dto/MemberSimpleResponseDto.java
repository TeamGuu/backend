package teamguu.backend.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.member.entity.Member;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberSimpleResponseDto {
    private String username;
    private String name;

    public static MemberSimpleResponseDto toDto(Member member) {
        return new MemberSimpleResponseDto(member.getUsername(), member.getName());
    }
}