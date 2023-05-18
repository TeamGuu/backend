package teamguu.backend.domain.stadium.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamguu.backend.domain.stadium.dto.SimpleStadiumInfoResponseDto;
import teamguu.backend.domain.stadium.dto.StadiumInfoResponseDto;
import teamguu.backend.domain.stadium.entity.Stadium;
import teamguu.backend.domain.stadium.repository.StadiumRepository;
import teamguu.backend.exception.situation.StadiumNotFoundException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public Page<SimpleStadiumInfoResponseDto> getSimpleStadiumInfoList(Pageable pageable) {
        return new PageImpl<>(stadiumRepository.findAll(pageable).get()
                .map(SimpleStadiumInfoResponseDto::from)
                .collect(Collectors.toList()),
                pageable,
                stadiumRepository.findAll(pageable).getTotalElements()
        );
    }

    public StadiumInfoResponseDto getStadiumInfo(Long stadiumId) {
        return StadiumInfoResponseDto.from(findStadium(stadiumId));
    }

    public Stadium findStadium(Long stadiumId) {
        return stadiumRepository.findById(stadiumId)
                .orElseThrow(StadiumNotFoundException::new);
    }
}
