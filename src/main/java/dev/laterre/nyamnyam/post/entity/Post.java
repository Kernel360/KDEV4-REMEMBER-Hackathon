package dev.laterre.nyamnyam.post.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.laterre.nyamnyam.file.entity.File;
import dev.laterre.nyamnyam.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String title;

  @Column
  private String content;

  @Column
  private String shopName;

  @Column
  private String address;

  @Column
  private String category;


  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany
  //@JoinColumn(name = "file_id")
  private List<File> file;

//  @ManyToOne
//  @JoinColumn(name= "board_id")
//  private Board board;

  private Long boardId;

  @Lob
  @Column(columnDefinition = "LONGTEXT")
  private String mediaData;
}
