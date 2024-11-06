package org.example.userservice.dto;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.userservice.model.Role;
import org.example.userservice.model.User;

import java.util.List;
@Getter
@Setter
public class UserDto {
    private String username;

    private String email;

List<Role> roles;
    private Boolean isEmailVerified;

    public static  UserDto getUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        userDto.setIsEmailVerified(user.getIsEmailVerified());
        return userDto;
    }

}
