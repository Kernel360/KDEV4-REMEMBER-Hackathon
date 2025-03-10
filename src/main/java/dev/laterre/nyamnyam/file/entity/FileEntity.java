package dev.laterre.nyamnyam.file.entity;

import dev.laterre.nyamnyam.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FILES")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orgName;

    @Column(nullable = false)
    private String uuidName;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

}
