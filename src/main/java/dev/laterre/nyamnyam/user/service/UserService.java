package dev.laterre.nyamnyam.user.service;

import dev.laterre.nyamnyam.user.entity.User;
import dev.laterre.nyamnyam.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registerUser(User user) {
    return userRepository.save(user);
  }
}
