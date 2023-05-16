package teamguu.backend.domain.stadium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.stadium.entity.Stadium;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StadiumInfoResponseDto {

    private SimpleStadiumInfoResponseDto simpleStadiumInfoResponseDto;
    private String phone;
    private String openTime;
    private String closeTime;

    public static StadiumInfoResponseDto from(Stadium stadium) {
        return StadiumInfoResponseDto.builder()
                .simpleStadiumInfoResponseDto(SimpleStadiumInfoResponseDto.from(stadium))
                .phone(stadium.getPhone())
                .openTime(stadium.getOpenTime())
                .closeTime(stadium.getCloseTime())
                .build();
    }
}
