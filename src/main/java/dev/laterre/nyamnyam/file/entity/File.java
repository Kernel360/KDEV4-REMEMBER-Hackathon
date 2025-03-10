package dev.laterre.nyamnyam.file.entity;

import dev.laterre.nyamnyam.post.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

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
  @JoinColumn(name = "post_id")
  private Post post;
}
