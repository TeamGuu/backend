package teamguu.backend.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamguu.backend.domain.member.service.MemberService;
import teamguu.backend.response.Response;

import static org.springframework.http.HttpStatus.*;
import static teamguu.backend.response.Response.*;
import static teamguu.backend.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
@Tag(name = "Member", description = "Member API Document")
public class MemberController {

    private MemberService memberService;

//    @Operation(summary = "Delete member API", description = "this is to delete member")
//    @ResponseStatus(OK)
//    @DeleteMapping("")
//    public Response deleteMember() {
//        memberService.deleteMember(memberService.getCurrentMember());
//        return success(SUCCESS_TO_DELETE_MEMBER);
//    }
//
//    @Operation(summary = "Change member profile image to new API", description = "put profile image what you want to change")
//    @ResponseStatus(OK)
//    @PostMapping("/profile-image-new")
//    public Response changeLogoImageToNew(@RequestPart MultipartFile profileImage) {
//        return success(SUCCESS_TO_CHANGE_MEMBER_PROFILE_IMAGE, memberService.changeLogoImageToNew(profileImage, memberService.getCurrentMember()));
//    }
//
//    @Operation(summary = "Change member profile image to basic API", description = "this is to change profile image to basic")
//    @ResponseStatus(OK)
//    @PostMapping("/profile-image-basic")
//    public Response changeLogoImageToBasic() {
//        memberService.changeProfileImageToBasic(memberService.getCurrentMember());
//        return success(SUCCESS_TO_CHANGE_MEMBER_PROFILE_IMAGE);
//    }
}
