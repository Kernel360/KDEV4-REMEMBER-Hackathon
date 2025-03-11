package dev.laterre.nyamnyam.user.controller;

import dev.laterre.nyamnyam.user.dto.UserDto;
import dev.laterre.nyamnyam.user.entity.User;
import dev.laterre.nyamnyam.user.mapper.UserMapper;
import dev.laterre.nyamnyam.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api")
@Slf4j
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping("/register")
  public ResponseEntity<UserDto.Response> registerUser(@Valid @RequestBody UserDto.Post register){
    User user = userMapper.userRegisterToUser(register);
    User registerUser = userService.registerUser(user);
    return ResponseEntity.ok(userMapper.userToUserResponseDto(registerUser));
  }
}