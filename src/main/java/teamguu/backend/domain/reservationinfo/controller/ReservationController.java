package teamguu.backend.domain.reservationinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import teamguu.backend.domain.reservationinfo.dto.ReserveRequestDto;
import teamguu.backend.domain.reservationinfo.service.ReservationInfoService;
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
@RequestMapping(value = "/api/reservation")
@Tag(name = "Reservation", description = "Reservation API Document")
public class ReservationController {

    private final ReservationInfoService reservationInfoService;
    private final TeamService teamService;
    private final StadiumService stadiumService;

    @Operation(summary = "Get reservation info List API")
    @ResponseStatus(OK)
    @GetMapping("")
    public Response getReservationInfos(Long stadiumId) {
        return success(SUCCESS_TO_GET_RESERVATION_INFOS,
                reservationInfoService.getReservationInfos(stadiumService.findStadium(stadiumId)));
    }

    @Operation(summary = "Reserve API")
    @ResponseStatus(CREATED)
    @PostMapping("")
    public Response reserve(Long teamId, Long stadiumId, @RequestBody ReserveRequestDto reserveRequestDto) {
        reservationInfoService.reserve(reserveRequestDto, teamService.findTeam(teamId), stadiumService.findStadium(stadiumId));
        return success(SUCCESS_TO_RESERVE_STADIUM);

    }

    // TODO 예약자가 취소하는건지 검증과정 필요, 하지만 현재 예약취소에 대한 기획이 없어서 보류
    @Operation(summary = "Cancel reservation API")
    @ResponseStatus(OK)
    @DeleteMapping("")
    public Response cancelReservation(Long reservationId) {
        reservationInfoService.cancelReservation(reservationId);
        return success(SUCCESS_TO_CANCEL_RESERVATION);
    }
}
