package dev.laterre.nyamnyam.post.repository;

import dev.laterre.nyamnyam.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByBoardId(Long boardId);


}
