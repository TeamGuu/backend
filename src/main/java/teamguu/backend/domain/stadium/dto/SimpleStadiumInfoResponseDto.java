package teamguu.backend.domain.stadium.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamguu.backend.domain.stadium.entity.Stadium;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleStadiumInfoResponseDto {

    private Long id;
    private String imageUrl;
    private String name;
    private String location;

    public static SimpleStadiumInfoResponseDto from(Stadium stadium) {
        return SimpleStadiumInfoResponseDto.builder()
                .id(stadium.getId())
                .imageUrl(stadium.getImageUrl())
                .name(stadium.getName())
                .location(stadium.getLocation())
                .build();
    }
}
