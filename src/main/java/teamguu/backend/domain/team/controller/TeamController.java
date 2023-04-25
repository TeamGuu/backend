package teamguu.backend.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamguu.backend.domain.member.service.MemberService;
import teamguu.backend.domain.team.dto.CreateTeamRequestDto;
import teamguu.backend.domain.team.service.TeamService;
import teamguu.backend.response.Response;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static teamguu.backend.response.Response.success;
import static teamguu.backend.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/team")
@Tag(name = "Team", description = "Team API Document")
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;

    @Operation(summary = "Create team API", description = "put your team info to create")
    @ResponseStatus(OK)
    @PostMapping("")
    public Response createTeam(@Valid @RequestBody CreateTeamRequestDto createTeamRequestDto) {
        teamService.createTeam(createTeamRequestDto, memberService.getCurrentMember());
        return success(SUCCESS_TO_CREATE_TEAM);
    }

    @Operation(summary = "Get team info API", description = "put team id what you want to see")
    @ResponseStatus(OK)
    @GetMapping("")
    public Response getTeamInfo(Long teamId) {
        return success(SUCCESS_TO_GET_TEAM_INFO, teamService.getTeamInfo(teamId));
    }

    @Operation(summary = "Get simple team info list API", description = "just send request")
    @ResponseStatus(OK)
    @GetMapping("/simple")
    public Response getSimpleTeamInfoList() {
        return success(SUCCESS_TO_GET_TEAM_INFO, teamService.getSimpleTeamInfoList(memberService.getCurrentMember().getId()));
    }

//    @Operation(summary = "Delete team API", description = "put team id what you want to delete")
//    @ResponseStatus(OK)
//    @DeleteMapping("")
//    public Response deleteTeam(Long teamId) {
//        teamService.deleteTeam(teamId);
//        return success(SUCCESS_TO_DELETE_TEAM);
//    }

//    @Operation(summary = "Change team logo image to new API", description = "put logo image and team ID what you want to change")
//    @ResponseStatus(OK)
//    @PostMapping("/logo-image-new")
//    public Response changeLogoImageToNew(@RequestPart MultipartFile logoImage, Long teamId) {
//        return success(SUCCESS_TO_CHANGE_TEAM_LOGO_IMAGE, teamService.changeLogoImageToNew(logoImage, teamId));
//    }

//    @Operation(summary = "Change team logo image to basic API", description = "put team ID what you want to change")
//    @ResponseStatus(OK)
//    @PostMapping("/logo-image-basic")
//    public Response changeLogoImageToBasic(Long teamId) {
//        teamService.changeLogoImageToBasic(teamId);
//        return success(SUCCESS_TO_CHANGE_TEAM_LOGO_IMAGE);
//    }
}
