package teamguu.backend.domain.match.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    WAITED("대기중"),
    COMPLETE("매칭완료");

    private final String name;

    private Status(String name) {
        this.name = name;
    }

    public static Status nameOf(String name) {
        for (Status status : Status.values()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }
}
