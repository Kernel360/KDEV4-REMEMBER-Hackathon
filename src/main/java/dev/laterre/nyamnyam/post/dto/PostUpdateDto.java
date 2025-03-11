package dev.laterre.nyamnyam.post.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostUpdateDto {

    private String title;

    private String content;

    private String address;

    private String category;

    // private List<File> file;
}
