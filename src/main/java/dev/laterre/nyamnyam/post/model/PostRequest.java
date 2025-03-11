package dev.laterre.nyamnyam.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class PostRequest {
    @NotBlank
    private Long boardId;

    @NotBlank
    private Long userId;

    private String address;

    @NotBlank
    private String category;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
