package org.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.model.Role;

import java.util.List;

@Getter
@Setter
public class SignUpResponseDto {
    private  String email;
    private String name;
    private List<Role> roles;
    private Boolean isEmailVerified;
}
