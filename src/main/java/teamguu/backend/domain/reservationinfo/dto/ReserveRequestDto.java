package teamguu.backend.domain.reservationinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.reservationinfo.entity.ReservationInfo;
import teamguu.backend.domain.stadium.entity.Stadium;
import teamguu.backend.domain.team.entity.Team;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveRequestDto {

    @NotBlank(message = "예약 날짜를 입력해주세요.")
    @Schema(description = "예약 날짜", defaultValue = "2023-05-01-08:00")
    private String date;

    public ReservationInfo toEntity(Team team, Stadium stadium) {
        return ReservationInfo.builder()
                .date(this.date)
                .team(team)
                .stadium(stadium)
                .build();
    }
}
