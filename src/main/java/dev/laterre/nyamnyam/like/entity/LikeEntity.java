package dev.laterre.nyamnyam.like.entity;

import dev.laterre.nyamnyam.post.entity.PostEntity;
import dev.laterre.nyamnyam.user.entity.UserEntity;
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
@Table(name = "LIKES")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
