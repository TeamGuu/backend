package teamguu.backend.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;
import static lombok.AccessLevel.*;
import static org.springframework.http.HttpStatus.*;

@JsonInclude(NON_NULL)
@AllArgsConstructor(access = PRIVATE)
@Schema(description = "This is response message from server")
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@Getter
public class Response {

    private Boolean isSuccess;
    private int code;
    private String message;
    private Object result;

    public static Response success(String message) {
        return new Response(true, OK.value(), message, null);
    }

    public static Response success(String message, Object data) {
        return new Response(true, OK.value(), message, data);
    }

    public static Response failure(HttpStatus status, String message) {
        return new Response(false, status.value(), message, null);
    }
}