package teamguu.backend.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamguu.backend.domain.member.dto.member.EditMemberInfoRequestDto;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.member.service.MemberService;
import teamguu.backend.response.Response;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static teamguu.backend.response.Response.*;
import static teamguu.backend.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
@Tag(name = "Member", description = "Member API Document")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "Get current member info API")
    @ResponseStatus(OK)
    @GetMapping("")
    public Response getCurrentMemberInfo() {
        return success(SUCCESS_TO_GET_CURRENT_MEMBER_INFO, memberService.getCurrentMemberInfo());
    }

    @Operation(summary = "Edit member API", description = "put info what you want to change")
    @ResponseStatus(OK)
    @PatchMapping("")
    public Response editMemberInfo(@Valid @RequestBody EditMemberInfoRequestDto editMemberRequestDto) {
        memberService.editMemberInfo(editMemberRequestDto);
        return success(SUCCESS_TO_EDIT_MEMBER_INFO);
    }

    @Operation(summary = "Delete member API", description = "this is to delete member")
    @ResponseStatus(OK)
    @DeleteMapping("")
    public Response deleteMember() {
        memberService.deleteMember();
        return success(SUCCESS_TO_DELETE_MEMBER);
    }

    @Operation(summary = "Change member profile image to new API", description = "put profile image what you want to change")
    @ResponseStatus(OK)
    @PostMapping(value = "/profile-image-new", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public Response changeProfileImageToNew(@RequestPart MultipartFile profileImage) {
        return success(SUCCESS_TO_CHANGE_MEMBER_PROFILE_IMAGE, memberService.changeProfileImageToNew(profileImage));
    }

    @Operation(summary = "Change member profile image to basic API", description = "this is to change profile image to basic")
    @ResponseStatus(OK)
    @PostMapping( "/profile-image-basic")
    public Response changeLogoImageToBasic() {
        memberService.changeProfileImageToBasic();
        return success(SUCCESS_TO_CHANGE_MEMBER_PROFILE_IMAGE);
    }
}
