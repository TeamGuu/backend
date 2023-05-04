package teamguu.backend.domain.matchinginfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import teamguu.backend.domain.matchinginfo.dto.CreateMatchingInfoRequestDto;
import teamguu.backend.domain.matchinginfo.service.MatchingInfoService;
import teamguu.backend.domain.team.service.TeamService;
import teamguu.backend.response.Response;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static teamguu.backend.response.Response.success;
import static teamguu.backend.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/matches")
@Tag(name = "Match", description = "Match API Document")
public class MatchingInfoController {

    private final MatchingInfoService matchingInfoService;
    private final TeamService teamService;

    @Operation(summary = "Create match API", description = "put your match info to create")
    @ResponseStatus(OK)
    @PostMapping()
    public Response createMatchingInfo(@Valid @RequestBody CreateMatchingInfoRequestDto createMatchingInfoRequestDto, Long teamId) {
        matchingInfoService.createMatchingInfo(createMatchingInfoRequestDto, teamService.findTeam(teamId));
        return success(SUCCESS_TO_CREATE_MATCH);
    }

    @Operation(summary = "Get simple match info list API", description = "just send request")
    @ResponseStatus(OK)
    @GetMapping("/simple")
    public Response getSimpleMatchingInfoList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        /**
         * TODO page, size 프론트 분들과 논의
         * TODO page, size parameter 형식이 아니라 Dto 형식으로 받아도 되나?
         */
        return success(SUCCESS_TO_GET_MATCH_INFO, matchingInfoService.getSimpleMatchingInfoList(pageable));
    }

    @Operation(summary = "Get match info API", description = "put match id what you want to see")
    @ResponseStatus(OK)
    @GetMapping()
    public Response getMatchingInfo(Long matchingInfoId) {
        return success(SUCCESS_TO_GET_MATCH_INFO, matchingInfoService.getMatchingInfo(matchingInfoId));
    }

    @Operation(summary = "Delete match info API", description = "put match id what you want to delete")
    @ResponseStatus(OK)
    @DeleteMapping()
    public Response deleteMatchingInfo(Long matchingInfoId) {
        matchingInfoService.deleteMatchingInfo(matchingInfoId);
        return success(SUCCESS_TO_DELETE_MATCH);
    }

    @Operation(summary = "Change match status to complete API", description = "put match id what you want to change")
    @ResponseStatus(OK)
    @PatchMapping("/status")
    public Response changeMatchingInfoStatusToComplete(Long matchingInfoId) {
        matchingInfoService.changeMatchingInfoStatusToComplete(matchingInfoId);
        return success(SUCCESS_TO_CHANGE_MATCH_STATUS);
    }
}
