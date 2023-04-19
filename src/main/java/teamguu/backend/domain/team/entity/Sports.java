package teamguu.backend.domain.team.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sports {
    SOCCER("축구"),
    BASEBALL("야구");

    private final String name;

    private Sports(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 한글명을 파라미터로 받아서 enum 형식으로 반환
    public static Sports nameOf(String name) {
        for (Sports sports : Sports.values()) {
            if (sports.getName().equals(name)) {
                return sports;
            }
        }
        return null;
    }
}