package dev.laterre.nyamnyam.user.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class UserDto {

  @Data
  public static class Post {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;
  }

  @Data
  public static class Response {

    private String email;
    private String nickname;
  }
}
