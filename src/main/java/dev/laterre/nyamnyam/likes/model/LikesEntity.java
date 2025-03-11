package dev.laterre.nyamnyam.likes.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "likes")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LikesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @ManyToOne
  @JoinColumn(name = "member_id")
  private Long memberId;

//  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Long postId;
}
