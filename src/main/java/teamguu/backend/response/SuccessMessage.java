package teamguu.backend.response;

import lombok.Getter;

@Getter
public class SuccessMessage {
    public static final String SUCCESS = "요청에 성공했습니다";
    public static final String SUCCESS_TO_VALIDATE_DUPLICATE = "중복 확인에 성공했습니다";
    public static final String SUCCESS_TO_SIGN_UP = "회원가입에 성공했습니다";
    public static final String SUCCESS_TO_SIGN_IN = "로그인에 성공했습니다";
    public static final String SUCCESS_TO_SIGN_OUT = "로그아웃에 성공했습니다";
    public static final String SUCCESS_TO_REISSUE = "토큰 재발급에 성공했습니다";
    public static final String SUCCESS_TO_CREATE_TEAM = "팀 생성에 성공했습니다";
    public static final String SUCCESS_TO_GET_TEAM_INFO = "팀 정보를 가져오는데 성공했습니다.";
    public static final String SUCCESS_TO_DELETE_TEAM = "팀을 삭제하는데 성공했습니다.";




}