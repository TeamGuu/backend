package teamguu.backend.domain.reservationinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamguu.backend.domain.reservationinfo.dto.ReservationInfoResponseDto;
import teamguu.backend.domain.reservationinfo.dto.ReserveRequestDto;
import teamguu.backend.domain.reservationinfo.repository.ReservationInfoRepository;
import teamguu.backend.domain.stadium.entity.Stadium;
import teamguu.backend.domain.team.entity.Team;
import teamguu.backend.exception.situation.ReservationInfoNotFondException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationInfoService {

    private final ReservationInfoRepository reservationInfoRepository;

    @Transactional
    public void reserve(ReserveRequestDto reserveRequestDto, Team team, Stadium stadium) {
        reservationInfoRepository.save(reserveRequestDto.toEntity(team, stadium));
    }

    @Transactional(readOnly = true)
    public List<ReservationInfoResponseDto> getReservationInfos(Stadium stadium) {
        return stadium.getReservationInfos().stream()
                .map(ReservationInfoResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelReservation(Long reservationInfoId) {
       reservationInfoRepository.delete(reservationInfoRepository.findById(reservationInfoId)
               .orElseThrow(ReservationInfoNotFondException::new));
    }

}
