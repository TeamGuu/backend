package teamguu.backend.domain.member.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMemberInfoRequestDto {
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    @Size(min = 2, message = "사용자 이름이 너무 짧습니다.")
    @Schema(description = "이름", defaultValue = "고길동")
    private String name;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "휴대폰 번호는 하이픈(-)을 포함해야 합니다.")
    @Schema(description = "휴대폰 번호", defaultValue = "010-5678-1234")
    private String phone;

    @NotBlank(message = "생년월일을 입력해주세요.")
    @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "생년월일은 하이픈(-)을 포함해야 합니다.")
    @Schema(description = "생년월일", defaultValue = "2000-01-01")
    private String birth;
}
