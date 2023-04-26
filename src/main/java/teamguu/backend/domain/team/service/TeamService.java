package teamguu.backend.domain.team.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//import teamguu.backend.config.aws.AmazonS3Service;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.team.dto.CreateTeamRequestDto;
import teamguu.backend.domain.team.dto.SimpleTeamInfoResponseDto;
import teamguu.backend.domain.team.dto.TeamInfoResponseDto;
import teamguu.backend.domain.team.entity.Team;
import teamguu.backend.domain.team.repository.TeamRepository;
import teamguu.backend.exception.situation.TeamNameAlreadyExistsException;
import teamguu.backend.exception.situation.TeamNotFoundException;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
//    private final AmazonS3Service amazonS3Service;

    //TODO 팀 인원의 평균나이를 구해주는 method 를 만들어야겠다
    @Transactional
    public void createTeam(CreateTeamRequestDto createTeamRequestDto, Member captain) {
        if (teamRepository.existsByName(createTeamRequestDto.getName())) {
            throw new TeamNameAlreadyExistsException(createTeamRequestDto.getName());
        }
        teamRepository.save(createTeamRequestDto.toEntity(captain));
    }

    @Transactional(readOnly = true)
    public TeamInfoResponseDto getTeamInfo(Long teamId) {
        return TeamInfoResponseDto.from(teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public List<SimpleTeamInfoResponseDto> getSimpleTeamInfoList(Long captainId) {
        return teamRepository.findTeamsByCaptainId(captainId)
                .stream()
                .map(SimpleTeamInfoResponseDto::from)
                .collect(toList());
    }

//    @Transactional
//    public void deleteTeam(Long teamId) {
//        Team foundTeam = teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
//        deleteLogoImageIfExits(foundTeam);
//        teamRepository.delete(foundTeam);
//    }

//    @Transactional
//    public String changeLogoImageToNew(MultipartFile logoImage, Long teamId){
//        Team foundTeam = findTeam(teamId);
//        String uploadedLogoImageUrl = amazonS3Service.uploadFile(logoImage);
//        deleteLogoImageIfExits(foundTeam);
//        return foundTeam.changeLogoImageUrl(uploadedLogoImageUrl);
//    }

//    @Transactional
//    public void changeLogoImageToBasic(Long teamId) {
//        Team foundTeam = findTeam(teamId);
//        foundTeam.changeLogoImageUrl("nothing");
//        amazonS3Service.deleteFile(foundTeam.getLogoImageUrl());
//    }

    public Team findTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
    }

//    private void deleteLogoImageIfExits(Team teamToCheck) {
//        if (!teamToCheck.getLogoImageUrl().equals("nothing")) {
//            amazonS3Service.deleteFile(teamToCheck.getLogoImageUrl());
//        }
//    }

}
