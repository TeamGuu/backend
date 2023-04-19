package teamguu.backend.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import teamguu.backend.domain.member.dto.sign.LoginRequestDto;
import teamguu.backend.domain.member.dto.sign.SignUpRequestDto;
import teamguu.backend.domain.member.dto.sign.TokenRequestDto;
import teamguu.backend.domain.member.dto.sign.ValidateSignUpRequestDto;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.member.repository.MemberRepository;
import teamguu.backend.domain.member.service.AuthService;
import teamguu.backend.domain.member.service.MemberService;
import teamguu.backend.response.Response;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static teamguu.backend.response.Response.success;
import static teamguu.backend.response.SuccessMessage.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Tag(name = "Auth", description = "Auth API Document")
public class AuthController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final AuthService authService;

    @Operation(summary = "Validate Duplicate API", description = "put your validate duplicate info")
    @ResponseStatus(OK)
    @PostMapping("/validate-duplicate")
    public Response validateDuplicateUsername(@Valid @RequestBody ValidateSignUpRequestDto validateSignUpRequestDto) {
        authService.duplicateValidate(validateSignUpRequestDto);
        return success(SUCCESS_TO_VALIDATE_DUPLICATE);
    }

    @Operation(summary = "Sign Up API", description = "put your sign up info.")
    @ResponseStatus(CREATED)
    @PostMapping("/sign-up")
    public Response signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        Member member = authService.signUp(signUpRequestDto);
        return success(SUCCESS_TO_SIGN_UP);
    }

    @Operation(summary = "Sign In API", description = "put your sign in info.")
    @PostMapping("/sign-in")
    @ResponseStatus(OK)
    public Response signIn(@Valid @RequestBody LoginRequestDto req) {
        return success(SUCCESS_TO_SIGN_IN, authService.signIn(req));
    }

    @Operation(summary = "logout API", description = "this is logout")
    @PostMapping("/logout")
    @ResponseStatus(OK)
    public Response logout() {
        Member currentMember = memberService.getCurrentMember();
        authService.logout(currentMember);
        return success(SUCCESS_TO_SIGN_OUT);
    }

    @Operation(summary = "Reissue API", description = "put your token info which including access token and refresh token.")
    @ResponseStatus(OK)
    @PostMapping("/reissue")
    public Response reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return success(SUCCESS_TO_REISSUE, authService.reissue(tokenRequestDto));
    }



}
