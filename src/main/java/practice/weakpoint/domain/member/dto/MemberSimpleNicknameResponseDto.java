package practice.weakpoint.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.weakpoint.domain.member.entity.Member;

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