package teamguu.backend.domain.reservationinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.reservationinfo.entity.ReservationInfo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfoResponseDto {
    private Long reservationId;
    private Long teamId;
    private String team;
    private String date;

    public static ReservationInfoResponseDto from(ReservationInfo reservationInfo) {
        return ReservationInfoResponseDto.builder()
                .reservationId(reservationInfo.getId())
                .teamId(reservationInfo.getTeam().getId())
                .team(reservationInfo.getTeam().getName())
                .date(reservationInfo.getDate())
                .build();
    }
}
