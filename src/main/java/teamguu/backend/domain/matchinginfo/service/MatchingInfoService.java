package teamguu.backend.domain.matchinginfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamguu.backend.domain.matchinginfo.dto.CreateMatchingInfoRequestDto;
import teamguu.backend.domain.matchinginfo.dto.MatchingInfoResponseDto;
import teamguu.backend.domain.matchinginfo.dto.SimpleMatchingInfoResponseDto;
import teamguu.backend.domain.matchinginfo.entity.MatchingInfo;
import teamguu.backend.domain.matchinginfo.repository.MatchingInfoRepository;
import teamguu.backend.domain.team.entity.Team;
import teamguu.backend.exception.situation.MatchingInfoNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingInfoService {

    private final MatchingInfoRepository matchingInfoRepository;

    @Transactional
    public void createMatchingInfo(CreateMatchingInfoRequestDto createMatchingInfoRequestDto, Team team) {
        matchingInfoRepository.save(createMatchingInfoRequestDto.toEntity(team));
    }

    public List<SimpleMatchingInfoResponseDto> getSimpleMatchingInfoList(Pageable pageable) {

        return matchingInfoRepository.findAll(pageable).stream()
                .map(SimpleMatchingInfoResponseDto::from)
                .collect(Collectors.toList());
    }

    public MatchingInfoResponseDto getMatchingInfo(Long matchingInfoId) {
        return MatchingInfoResponseDto.from(findMatchingInfo(matchingInfoId));
    }

    @Transactional
    public void deleteMatchingInfo(Long matchingInfoId) {
        matchingInfoRepository.delete(findMatchingInfo(matchingInfoId));
    }

    @Transactional
    public void changeMatchingInfoStatusToComplete(Long matchingInfoId) {
       findMatchingInfo(matchingInfoId).changeStatusToComplete();
    }

    public MatchingInfo findMatchingInfo(Long matchingInfoId) {
        return matchingInfoRepository.findById(matchingInfoId).orElseThrow(MatchingInfoNotFoundException::new);
    }
}
