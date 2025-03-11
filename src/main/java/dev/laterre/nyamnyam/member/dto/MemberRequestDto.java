package dev.laterre.nyamnyam.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String memberRealId;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String role;
}
