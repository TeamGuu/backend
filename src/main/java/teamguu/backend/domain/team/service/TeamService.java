package teamguu.backend.domain.team.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import teamguu.backend.config.aws.AmazonS3Service;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.team.dto.CreateTeamRequestDto;
import teamguu.backend.domain.team.dto.EditTeamInfoRequestDto;
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
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final AmazonS3Service amazonS3Service;

    //TODO 팀 인원의 평균나이를 구해주는 method 를 만들어야겠다
    public void createTeam(CreateTeamRequestDto createTeamRequestDto, Member captain) {
        if (teamRepository.existsByName(createTeamRequestDto.getName())) {
            throw new TeamNameAlreadyExistsException(createTeamRequestDto.getName());
        }
        teamRepository.save(createTeamRequestDto.toEntity(captain));
    }

    @Transactional(readOnly = true)
    public TeamInfoResponseDto getTeamInfo(Long teamId) {
        return TeamInfoResponseDto.from(findTeam(teamId));
    }

    @Transactional(readOnly = true)
    public List<SimpleTeamInfoResponseDto> getSimpleTeamInfoList(Long captainId) {
        return teamRepository.findTeamsByCaptainId(captainId)
                .stream()
                .map(SimpleTeamInfoResponseDto::from)
                .collect(toList());
    }

    public void editTeamInfo(EditTeamInfoRequestDto editTeamInfoRequestDto, Long teamId) {
        Team findTeam = findTeam(teamId);
        validateDuplicateByName(editTeamInfoRequestDto, findTeam);
        findTeam.editTeam(editTeamInfoRequestDto.getName(), editTeamInfoRequestDto.getHistory(),
                editTeamInfoRequestDto.getIntro(), editTeamInfoRequestDto.getPlayerInfo());
    }

    public void deleteTeam(Long teamId) {
        Team foundTeam = findTeam(teamId);
        deleteLogoImageIfExits(foundTeam);
        teamRepository.delete(foundTeam);
    }

    public String changeLogoImageToNew(MultipartFile logoImage, Long teamId){
        Team foundTeam = findTeam(teamId);
        String uploadedLogoImageUrl = amazonS3Service.uploadFile(logoImage);
        deleteLogoImageIfExits(foundTeam);
        return foundTeam.changeLogoImageUrl(uploadedLogoImageUrl);
    }

    public void changeLogoImageToBasic(Long teamId) {
        Team foundTeam = findTeam(teamId);
        String deleteLogoImageUrl = foundTeam.getLogoImageUrl();
        // TODO S3에 기본 이미지 저장 후 확장자 추가 (Ex. basic.JPEG) -> 이미지 이름을 Member 와 다르게 해야할 듯?
        foundTeam.changeLogoImageUrl("basic");
        amazonS3Service.deleteFile(deleteLogoImageUrl);
    }

    @Transactional(readOnly = true)
    public Team findTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
    }

    private void validateDuplicateByName(EditTeamInfoRequestDto editTeamInfoRequestDto, Team findTeam) {
        if (!editTeamInfoRequestDto.getName().equals(findTeam.getName())) {
            if (teamRepository.existsByName(editTeamInfoRequestDto.getName())) {
                throw new TeamNameAlreadyExistsException(editTeamInfoRequestDto.getName());
            }
        }
    }

    private void deleteLogoImageIfExits(Team teamToCheck) {
        if (!teamToCheck.getLogoImageUrl().equals("basic")) {
            amazonS3Service.deleteFile(teamToCheck.getLogoImageUrl());
        }
    }
}
