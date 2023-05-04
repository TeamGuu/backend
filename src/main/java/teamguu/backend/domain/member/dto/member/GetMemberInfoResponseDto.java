package teamguu.backend.domain.member.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import teamguu.backend.domain.member.entity.Member;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMemberInfoResponseDto {
    @Schema(description = "멤버 ID")
    private Long id;
    @Schema(description = "멤버 이메일")
    private String username;
    @Schema(description = "멤버 이름")
    private String name;
    @Schema(description = "멤버 전화번호")
    private String phone;
    @Schema(description = "멤버 생년월일")
    private String birth;
    @Schema(description = "멤버 프로필 이미지 URL")
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
