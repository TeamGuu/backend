package practice.weakpoint.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;
import static lombok.AccessLevel.*;
import static org.springframework.http.HttpStatus.*;

@JsonInclude(NON_NULL) //  null 값을 가지는 필드는, JSON 응답에 포함되지 않음
@AllArgsConstructor(access = PRIVATE)
@Schema(description = "This is response from server.")
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@Getter
public class Response {

    private boolean isSuccess;
    private int code;
    private String message;
    private Result result;

    public static Response success(String msg) { // 4
        return new Response(true, OK.value(), msg, null);
    }

    public static <T> Response success(String msg, T data) { // 5
        return new Response(true, OK.value(), msg, new Success<>(data));
    }

    public static Response failure(HttpStatus status, String msg) { // 6
        return new Response(false, status.value(), msg, null);
    }
}