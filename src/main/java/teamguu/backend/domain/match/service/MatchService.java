package teamguu.backend.domain.match.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamguu.backend.domain.match.dto.CreateMatchRequestDto;
import teamguu.backend.domain.match.dto.MatchInfoResponseDto;
import teamguu.backend.domain.match.dto.SimpleMatchInfoResponseDto;
import teamguu.backend.domain.match.entity.Match;
import teamguu.backend.domain.match.repository.MatchRepository;
import teamguu.backend.domain.team.entity.Team;
import teamguu.backend.exception.situation.MatchInfoNotFoundException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public void createMatch(CreateMatchRequestDto createMatchRequestDto, Team team) {
        matchRepository.save(createMatchRequestDto.toEntity(team));
    }

    public Page<SimpleMatchInfoResponseDto> getSimpleMatchInfos(Pageable pageable) {
        return new PageImpl<>(matchRepository.findAll(pageable).get()
                .map(SimpleMatchInfoResponseDto::from)
                .collect(Collectors.toList()),
                pageable,
                matchRepository.findAll(pageable).getTotalElements());
    }

    @Transactional(readOnly = true)
    public MatchInfoResponseDto getMatchInfo(Long matchId) {
        return MatchInfoResponseDto.from(matchRepository.findById(matchId).orElseThrow(MatchInfoNotFoundException::new));
    }

    public void deleteMatch(Long matchId) {
        matchRepository.delete(getMatch(matchId));
    }

    @Transactional
    public void changeMatchStatusToComplete(Long matchId) {
       getMatch(matchId).changeStatusToComplete();
    }

    public Match getMatch(Long matchId) {
        return matchRepository.findById(matchId).orElseThrow(MatchInfoNotFoundException::new);
    }
}
