package dev.laterre.nyamnyam.likes.repository;

import dev.laterre.nyamnyam.likes.model.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity, Long> {
    Optional<LikesEntity> findByMemberId(Long memberId);
    Optional<LikesEntity> findByPostId(Long postId);
    boolean existsByMemberIdAndPostId(Long memberId, Long postId);
    long countByPostId(Long postId);
}
