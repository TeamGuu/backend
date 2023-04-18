package teamguu.backend.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import teamguu.backend.domain.member.dto.sign.ValidateSignUpRequestDto;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.member.service.AuthService;
import teamguu.backend.domain.member.service.MemberService;
import teamguu.backend.domain.team.dto.CreateTeamRequestDto;
import teamguu.backend.domain.team.service.TeamService;
import teamguu.backend.response.Response;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static teamguu.backend.response.Response.success;
import static teamguu.backend.response.SuccessMessage.SUCCESS_TO_CREATE_TEAM;
import static teamguu.backend.response.SuccessMessage.SUCCESS_TO_VALIDATE_DUPLICATE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/team")
@Tag(name = "Team", description = "Team API Document")
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;

    @Operation(summary = "Create Team API", description = "put your team info to create")
    @ResponseStatus(OK)
    @PostMapping("")
    public Response createTeam(@Valid @RequestBody CreateTeamRequestDto createTeamRequestDto) {
        Member currentMember = memberService.getCurrentUser();
        teamService.createTeam(createTeamRequestDto, currentMember);
        return success(SUCCESS_TO_CREATE_TEAM);
    }
}
