package teamguu.backend.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamguu.backend.domain.reservation.dto.ReservationInfoResponseDto;
import teamguu.backend.domain.reservation.dto.ReserveRequestDto;
import teamguu.backend.domain.reservation.repository.ReservationRepository;
import teamguu.backend.domain.stadium.entity.Stadium;
import teamguu.backend.domain.team.entity.Team;
import teamguu.backend.exception.situation.ReservationInfoNotFondException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public void reserve(ReserveRequestDto reserveRequestDto, Team team, Stadium stadium) {
        reservationRepository.save(reserveRequestDto.toEntity(team, stadium));
    }

    public List<ReservationInfoResponseDto> getReservationInfos(Stadium stadium) {
        return reservationRepository.findReservationsByStadium(stadium).stream()
                .map(ReservationInfoResponseDto::from)
                .collect(Collectors.toList());
    }

    public void cancelReservation(Long reservationId) {
       reservationRepository.delete(reservationRepository.findById(reservationId)
               .orElseThrow(ReservationInfoNotFondException::new));
    }
}
