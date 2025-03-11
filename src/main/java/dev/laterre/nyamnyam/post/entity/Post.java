package dev.laterre.nyamnyam.post.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.laterre.nyamnyam.file.entity.File;
import dev.laterre.nyamnyam.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

}
