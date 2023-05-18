package teamguu.backend.response;

import lombok.Getter;

@Getter
public class SuccessMessage {
    public static final String SUCCESS = "요청에 성공했습니다";
    public static final String SUCCESS_TO_VALIDATE_DUPLICATE = "중복 확인에 성공했습니다.";
    public static final String SUCCESS_TO_SIGN_UP = "회원가입에 성공했습니다.";
    public static final String SUCCESS_TO_SIGN_IN = "로그인에 성공했습니다.";
    public static final String SUCCESS_TO_LOGOUT = "로그아웃에 성공했습니다.";
    public static final String SUCCESS_TO_REISSUE = "토큰 재발급에 성공했습니다.";
    public static final String SUCCESS_TO_EDIT_MEMBER_INFO = "회원 프로필 정보를 변경하는데 성공했습니다.";
    public static final String SUCCESS_TO_GET_CURRENT_MEMBER_INFO = "현재 회원의 정보를 가져오는데 성공했습니다.";
    public static final String SUCCESS_TO_DELETE_MEMBER = "회원을 삭제하는데 성공했습니다.";
    public static final String SUCCESS_TO_CHANGE_MEMBER_PROFILE_IMAGE = "회원 프로필 이미지를 변경하는데 성공했습니다.";
    public static final String SUCCESS_TO_CREATE_TEAM = "팀 생성에 성공했습니다.";
    public static final String SUCCESS_TO_GET_TEAM_INFO = "팀 정보를 가져오는데 성공했습니다.";
    public static final String SUCCESS_TO_EDIT_TEAM_INFO = "팀 정보를 변경하는데 성공했습니다.";
    public static final String SUCCESS_TO_DELETE_TEAM = "팀을 삭제하는데 성공했습니다.";
    public static final String SUCCESS_TO_CHANGE_TEAM_LOGO_IMAGE = "팀 로고 이미지를 변경 하는데 성공했습니다.";
    public static final String SUCCESS_TO_CREATE_MATCH = "매칭 공고문 생성에 성공했습니다.";
    public static final String SUCCESS_TO_GET_MATCH_INFO = "매칭 공고문 정보를 가져오는데 성공했습니다.";
    public static final String SUCCESS_TO_CHANGE_MATCH_STATUS = "매칭 공고문 상태를 변경하는데 성공했습니다.";
    public static final String SUCCESS_TO_DELETE_MATCH = "매칭 공고문을 삭제하는데 성공했습니다.";
    public static final String SUCCESS_TO_GET_STADIUM_INFO = "경기장 정보를 가져오는데 성공했습니다.";
    public static final String SUCCESS_TO_RESERVE_STADIUM = "경기장 예약에 성공했습니다.";
    public static final String SUCCESS_TO_CANCEL_RESERVATION = "경기장 예약을 취소하는데 성공했습니다.";
    public static final String SUCCESS_TO_GET_RESERVATION_INFOS = "경기장의 예약 정보 목록을 가져오는데 성공했습니다.";


}