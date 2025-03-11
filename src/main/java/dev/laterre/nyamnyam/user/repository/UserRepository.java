package dev.laterre.nyamnyam.user.repository;

import dev.laterre.nyamnyam.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
