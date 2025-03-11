package dev.laterre.nyamnyam.user.mapper;

import dev.laterre.nyamnyam.user.dto.UserDto;
import dev.laterre.nyamnyam.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
  User userRegisterToUser(UserDto.Post register);
  UserDto.Response userToUserResponseDto(User user);
}
