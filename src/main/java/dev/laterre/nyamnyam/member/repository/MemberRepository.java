package dev.laterre.nyamnyam.member.repository;

import dev.laterre.nyamnyam.member.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByMemberRealId(String memberRealId);

    boolean existsByMemberRealId(String memberRealId);
    boolean existsByNickname(String nickName);
}
