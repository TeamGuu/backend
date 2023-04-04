package teamguu.backend.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.member.entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSimpleNicknameResponseDto {
    private String name;
    private String nickname;

    public static MemberSimpleNicknameResponseDto toDto(Member member) {
        return new MemberSimpleNicknameResponseDto(member.getName(), member.getNickname());
    }
}