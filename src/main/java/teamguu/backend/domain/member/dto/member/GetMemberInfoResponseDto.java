package teamguu.backend.domain.member.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import teamguu.backend.domain.member.entity.Member;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMemberInfoResponseDto {
    private Long id;
    private String username;
    private String name;
    private String phone;
    private String birth;
    private String profileImageUrl;

    public static GetMemberInfoResponseDto toDto(Member member) {
        return GetMemberInfoResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .username(member.getUsername())
                .phone(member.getPhone())
                .birth(member.getBirth())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }
}
