package teamguu.backend.domain.member.dto.sign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogoutRequestDto {

    private String accessToken;
}
