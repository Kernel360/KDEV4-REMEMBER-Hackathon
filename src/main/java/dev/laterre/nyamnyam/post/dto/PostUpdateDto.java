package dev.laterre.nyamnyam.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateDto {

    private String title;

    private String content;

    private String address;

    private String category;

    // private List<File> file;
}
