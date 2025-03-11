package dev.laterre.nyamnyam.post.db;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name="post")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;

    private Long userId;

    private String address;

    private String category;

    private String content;

    private String title;

}
