package dev.laterre.nyamnyam.reply.entity;

import dev.laterre.nyamnyam.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REPLIES")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime repliedAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
