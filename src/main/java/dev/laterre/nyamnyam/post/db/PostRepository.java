package dev.laterre.nyamnyam.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends  JpaRepository<PostEntity, Long>{

    List<PostEntity> findByBoardId(Long boardId);
}
