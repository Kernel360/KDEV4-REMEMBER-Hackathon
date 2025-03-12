package dev.laterre.nyamnyam.post.repository;

import dev.laterre.nyamnyam.post.model.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    public List<PostEntity> findByBoardId(Long boardId);

    Page<PostEntity> findByBoardId(Long boardId, Pageable pageable);
}
