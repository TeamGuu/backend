package teamguu.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import teamguu.backend.exception.situation.*;
import teamguu.backend.response.Response;


import javax.management.relation.RoleNotFoundException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;
import static teamguu.backend.response.Response.*;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    // 500 에러
    @ExceptionHandler(IllegalArgumentException.class) // 지정한 예외가 발생하면 해당 메소드 실행
    @ResponseStatus(INTERNAL_SERVER_ERROR) // 각 예외마다 상태 코드 지정
    public Response illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        log.info("e = {}", e.getMessage());
        return failure(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 500 에러
    // 컨버트 실패
    @ExceptionHandler(CannotConvertHelperException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Response cannotConvertNestedStructureException(CannotConvertHelperException e) {
        log.error("e = {}", e.getMessage());
        return failure(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 400 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) { // 2
        return failure(BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    // 400 에러
    // 토큰 만료
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response tokenExpiredException() {
        return failure(BAD_REQUEST, "토큰이 만료되었습니다.");
    }

    // 400 에러
    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response bindException(BindException e) {
        return failure(BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }


    // 401 응답
    // 아이디 혹은 비밀번호 오류시 발생
    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(UNAUTHORIZED)
    public Response loginFailureException() {
        return failure(UNAUTHORIZED, "로그인에 실패하였습니다.");
    }

    // 401 응답
    // 요청자와 요청한 유저의 정보가 일치하지 않을시에 발생
    @ExceptionHandler(MemberNotEqualsException.class)
    @ResponseStatus(UNAUTHORIZED)
    public Response memberNotEqualsException() {
        return failure(UNAUTHORIZED, "유저 정보가 일치하지 않습니다.");
    }


    // 404 응답
    // 요청한 유저를 찾을 수 없음
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response memberNotFoundException() {
        return failure(NOT_FOUND, "요청한 회원을 찾을 수 없습니다.");
    }


    // 404 응답
    // 요청한 자원을 찾을 수 없음
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response roleNotFoundException() {
        return failure(NOT_FOUND, "요청한 권한 등급을 찾을 수 없습니다.");
    }


    // 404 응답
    // Image 형식 지원하지 않음
    @ExceptionHandler(UnsupportedImageFormatException.class)
    @ResponseStatus(NOT_FOUND)
    public Response unsupportedImageFormatException() {
        return failure(NOT_FOUND, "이미지 형식을 지원하지 않습니다.");
    }

    // 400 응답
    // 파일 확장자가 존재하지 않음
    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response stringIndexOutOfBoundsException() {
        return failure(BAD_REQUEST, "파일 확장자가 존재하지 않습니다.");
    }

    // 404 응답
    // 파일 업로드 실패
    @ExceptionHandler(FileUploadFailureException.class)
    @ResponseStatus(NOT_FOUND)
    public Response fileUploadFailureException(FileUploadFailureException e) {
        log.error("e = {}", e.getMessage());
        return failure(NOT_FOUND, "이미지 업로드 실패");
    }

    // 409 응답
    // username 중복
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public Response memberEmailAlreadyExistsException(UsernameAlreadyExistsException e) {
        return failure(CONFLICT, e.getMessage() + "은 중복된 아이디 입니다.");
    }

    // 409 응답
    // team name 중복
    @ExceptionHandler(TeamNameAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public Response teamNameAlreadyExistsException(TeamNameAlreadyExistsException e) {
        return failure(CONFLICT, e.getMessage() + "은 중복된 팀 이름 입니다.");
    }

    // 404 응답
    // 요청한 팀을 찾을 수 없음
    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response teamNotFoundException() {
        return failure(NOT_FOUND, "요청한 팀을 찾을 수 없습니다.");
    }

    // 404 응답
    // 파일이 비어있음
    @ExceptionHandler(EmptyFileException.class)
    @ResponseStatus(NOT_FOUND)
    public Response emptyFileException() {
        return failure(NOT_FOUND, "파일이 비어있습니다.");
    }

    // 404 응답
    // 요청한 매칭 공고문을 찾을 수 없음
    @ExceptionHandler(MatchingInfoNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response matchingInfoNotFoundException() {
        return failure(NOT_FOUND, "요청한 매칭 공고문을 찾을 수 없습니다.");
    }

    // 404 응답
    // 요청한 경기장을 찾을 수 없음
    @ExceptionHandler(StadiumNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response stadiumNotFoundException() {
        return failure(NOT_FOUND, "요청한 경기장을 찾을 수 없습니다.");
    }

    // 404 응답
    // 요청한 매칭 공고문을 찾을 수 없음
    @ExceptionHandler(ReservationInfoNotFondException.class)
    @ResponseStatus(NOT_FOUND)
    public Response reservationInfoNotFoundException() {
        return failure(NOT_FOUND, "요청한 예약 정보를 찾을 수 없습니다.");
    }
}
