package teamguu.backend.domain.reservation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import teamguu.backend.domain.reservation.dto.ReserveRequestDto;
import teamguu.backend.domain.reservation.service.ReservationService;
import teamguu.backend.domain.stadium.service.StadiumService;
import teamguu.backend.domain.team.service.TeamService;
import teamguu.backend.response.Response;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static teamguu.backend.response.Response.success;
import static teamguu.backend.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/reservations")
@Tag(name = "Reservation", description = "Reservation API Document")
public class ReservationController {

    private final ReservationService reservationService;
    private final TeamService teamService;
    private final StadiumService stadiumService;

    @Operation(summary = "Get reservation info List API", description = "put stadium id what you want to see")
    @ResponseStatus(OK)
    @GetMapping("")
    public Response getReservationInfos(Long stadiumId) {
        return success(SUCCESS_TO_GET_RESERVATION_INFOS,
                reservationService.getReservationInfos(stadiumService.getStadium(stadiumId)));
    }

    @Operation(summary = "Reserve API", description = "put your reservation info to create with team id and stadium id")
    @ResponseStatus(CREATED)
    @PostMapping("")
    public Response reserve(Long teamId, Long stadiumId, @RequestBody ReserveRequestDto reserveRequestDto) {
        reservationService.reserve(reserveRequestDto, teamService.getTeam(teamId), stadiumService.getStadium(stadiumId));
        return success(SUCCESS_TO_RESERVE_STADIUM);

    }

    // TODO 예약자가 취소하는건지 검증과정 필요, 하지만 현재 예약취소에 대한 기획이 없어서 보류
    @Operation(summary = "Cancel reservation API", description = "put reservation id to cancel")
    @ResponseStatus(OK)
    @DeleteMapping("")
    public Response cancelReservation(Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return success(SUCCESS_TO_CANCEL_RESERVATION);
    }
}
