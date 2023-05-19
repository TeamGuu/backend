package teamguu.backend.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.reservation.entity.Reservation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationInfoResponseDto {
    private Long reservationId;
    private Long teamId;
    private String team;
    private String date;

    public static ReservationInfoResponseDto from(Reservation reservation) {
        return ReservationInfoResponseDto.builder()
                .reservationId(reservation.getId())
                .teamId(reservation.getTeam().getId())
                .team(reservation.getTeam().getName())
                .date(reservation.getDate())
                .build();
    }
}
