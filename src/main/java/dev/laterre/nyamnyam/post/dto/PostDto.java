package dev.laterre.nyamnyam.post.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {

    @NotBlank
    private Long boardId;

    @NotBlank
    private Long memberId;

    private String address;

    private String shopName;

    @NotBlank
    private String category;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String mediaData;
}
