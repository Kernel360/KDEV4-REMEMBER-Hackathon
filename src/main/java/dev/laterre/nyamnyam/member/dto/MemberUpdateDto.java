package dev.laterre.nyamnyam.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {

    private String email;
    private String password;
    private String nickname;
    private String role;
}
