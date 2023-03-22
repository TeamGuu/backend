package practice.weakpoint.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import practice.weakpoint.domain.member.Member;
import practice.weakpoint.dto.sign.LoginRequestDto;
import practice.weakpoint.dto.sign.SignUpRequestDto;
import practice.weakpoint.dto.sign.TokenRequestDto;
import practice.weakpoint.response.Response;
import practice.weakpoint.service.AuthService;

import static org.springframework.http.HttpStatus.*;
import static practice.weakpoint.response.Response.*;
import static practice.weakpoint.response.SuccessMessage.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Tag(name = "Auth", description = "Auth API Document")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Sign Up API", description = "put your sign up info.")
    @ResponseStatus(CREATED)
    @PostMapping("/sign-up")
    public Response register(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        Member member = authService.signup(signUpRequestDto);
        return success(SUCCESS_TO_SIGN_UP);
    }

    @Operation(summary = "Sign In API", description = "put your sign in info.")
    @PostMapping("/sign-in")
    @ResponseStatus(OK)
    public Response signIn(@Valid @RequestBody LoginRequestDto req) {
        return success(SUCCESS_TO_SIGN_IN, authService.signIn(req));
    }

    @Operation(summary = "Reissue API", description = "put your token info which including access token and refresh token.")
    @ResponseStatus(OK)
    @PostMapping("/reissue")
    public Response reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return success(SUCCESS_TO_REISSUE, authService.reissue(tokenRequestDto));
    }

    @Operation(summary = "Test API", description = "testing swagger configuration.")
    @GetMapping("/test")
    public String testSwagger( ) {
        return "Ok";
    }


}
