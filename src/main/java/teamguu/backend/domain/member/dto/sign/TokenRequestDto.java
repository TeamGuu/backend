package teamguu.backend.domain.member.dto.sign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenRequestDto {

    private String accessToken;
    private String refreshToken;
}
