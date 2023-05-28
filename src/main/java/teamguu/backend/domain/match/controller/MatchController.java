package teamguu.backend.domain.match.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import teamguu.backend.domain.match.dto.CreateMatchRequestDto;
import teamguu.backend.domain.match.service.MatchService;
import teamguu.backend.domain.member.service.MemberService;
import teamguu.backend.domain.team.service.TeamService;
import teamguu.backend.response.Response;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static teamguu.backend.response.Response.success;
import static teamguu.backend.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/matches")
@Tag(name = "Match", description = "Match API Document")
public class MatchController {

    private final MatchService matchService;
    private final TeamService teamService;
    private final MemberService memberService;

    @Operation(summary = "Create match API", description = "put your match info to create")
    @ResponseStatus(CREATED)
    @PostMapping()
    public Response createMatchInfo(@Valid @RequestBody CreateMatchRequestDto createMatchRequestDto, Long teamId) {
        matchService.createMatch(createMatchRequestDto, teamService.getTeam(teamId));
        return success(SUCCESS_TO_CREATE_MATCH);
    }

    @Operation(summary = "Get simple match info list API", description = "put page info what you want")
    @ResponseStatus(OK)
    @GetMapping("/simple")
    @PageableAsQueryParam
    public Response getSimpleMatchInfos(Pageable pageable) {
        return success(SUCCESS_TO_GET_SIMPLE_MATCH_INFOS, matchService.getSimpleMatchInfos(pageable));
    }

    @Operation(summary = "Get match info API", description = "put match id what you want to see")
    @ResponseStatus(OK)
    @GetMapping()
    public Response getMatchInfo(Long matchId) {
        return success(SUCCESS_TO_GET_MATCH_INFO, matchService.getMatchInfo(matchId));
    }

    @Operation(summary = "Delete match info API", description = "put match id what you want to delete")
    @ResponseStatus(OK)
    @DeleteMapping()
    public Response deleteMatch(Long matchId) {
        matchService.deleteMatch(matchId);
        return success(SUCCESS_TO_DELETE_MATCH);
    }

    @Operation(summary = "Get Simple current member info API", description = "chat the person who you want to chat with")
    @ResponseStatus(OK)
    @GetMapping("/chat")
    public Response getChatMemberInfo(Long matchId) {  // TODO 채팅 보낸 사용자가 소속된 팀이 있는지 검증
        return success(SUCCESS_TO_GET_CHAT_MEMBER_INFO, matchService.getChatMemberInfo(matchId, memberService.getCurrentMember()));
    }

    @Operation(summary = "Change match status to complete API", description = "put match id what you want to change")
    @ResponseStatus(OK)
    @PatchMapping("/status")
    public Response changeMatchStatusToComplete(Long matchId) {
        matchService.changeMatchStatusToComplete(matchId);
        return success(SUCCESS_TO_CHANGE_MATCH_STATUS);
    }
}
