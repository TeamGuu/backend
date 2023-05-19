package teamguu.backend.domain.stadium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import teamguu.backend.domain.stadium.service.StadiumService;
import teamguu.backend.response.Response;

import static org.springframework.http.HttpStatus.OK;
import static teamguu.backend.response.SuccessMessage.SUCCESS_TO_GET_SIMPLE_STADIUM_INFOS;
import static teamguu.backend.response.SuccessMessage.SUCCESS_TO_GET_STADIUM_INFO;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/stadiums")
@Tag(name = "Stadium", description = "Stadium API Document")
public class StadiumController {

    private final StadiumService stadiumService;

    @Operation(summary = "Get simple stadium info list API", description = "put page info what you want")
    @ResponseStatus(OK)
    @PageableAsQueryParam
    @GetMapping("/simple")
    public Response getSimpleStadiumInfos(Pageable pageable) {
        return Response.success(SUCCESS_TO_GET_SIMPLE_STADIUM_INFOS, stadiumService.getSimpleStadiumInfos(pageable));
    }

    @Operation(summary = "Get stadium info API", description = "put stadium id what you want to see")
    @ResponseStatus(OK)
    @GetMapping()
    public Response getStadiumInfo(Long stadiumId) {
        return Response.success(SUCCESS_TO_GET_STADIUM_INFO, stadiumService.getStadiumInfo(stadiumId));
    }
}
