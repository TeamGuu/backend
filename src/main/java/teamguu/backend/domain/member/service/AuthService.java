package teamguu.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teamguu.backend.config.jwt.JwtProvider;
import teamguu.backend.config.redis.RedisService;
import teamguu.backend.domain.member.dto.sign.*;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.domain.member.repository.MemberRepository;
import teamguu.backend.exception.situation.UsernameAlreadyExistsException;

import javax.transaction.Transactional;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisService redisService;
    private final JwtProvider jwtProvider;

    public void validateDuplicate(ValidateSignUpRequestDto validateSignUpRequestDto) {
        validateDuplicateByUsername(validateSignUpRequestDto);
    }

    public void signUp(SignUpRequestDto req) {
        validateSignUpInfo(req);
        memberRepository.save(req.toEntity(passwordEncoder));
    }

    public TokenResponseDto signIn(LoginRequestDto req) {
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(req.toAuthentication());
        TokenDto tokenDto = authorize(authentication);
        return new TokenResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }

    public void logout(Member member) {
        redisService.deleteValues("RT: " + member.getUsername());
    }

    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto) {
        validateRefreshToken(tokenRequestDto);
        Authentication authentication = jwtProvider.getAuthentication(tokenRequestDto.getAccessToken());
        validateRefreshTokenOwner(authentication, tokenRequestDto);

        TokenDto tokenDto = authorize(authentication);
        return new TokenResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }

    private void validateDuplicateByUsername(ValidateSignUpRequestDto validateSignUpRequestDto) {
        if (memberRepository.existsByUsername(validateSignUpRequestDto.getUsername())) {
            throw new UsernameAlreadyExistsException(validateSignUpRequestDto.getUsername());
        }
    }

    private void validateSignUpInfo(SignUpRequestDto req) {
        if(memberRepository.existsByUsername(req.getUsername())) {
            throw new UsernameAlreadyExistsException(req.getUsername());
        }
    }

    private TokenDto authorize(Authentication authentication) {
        TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);
        redisService.setValues("RT: " + authentication.getName(), tokenDto.getRefreshToken(), Duration.ofMillis(tokenDto.getRefreshTokenExpiresIn()));
        return tokenDto;
    }

    private void validateRefreshToken(TokenRequestDto tokenRequestDto) {
        if (!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }
    }

    private void validateRefreshTokenOwner(Authentication authentication, TokenRequestDto tokenRequestDto) {
        if (!redisService.getValues("RT: " + authentication.getName()).equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }
    }
}
