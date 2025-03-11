package dev.laterre.nyamnyam.member.repository;

import dev.laterre.nyamnyam.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
