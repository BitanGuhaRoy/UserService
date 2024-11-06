package org.example.userservice.services;

import org.example.userservice.dto.LogInResponseDto;
import org.example.userservice.dto.SignUpResponseDto;
import org.example.userservice.dto.UserDto;
import org.example.userservice.model.Token;
import org.example.userservice.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
     SignUpResponseDto signUp(String email, String password, String name);

     LogInResponseDto login(String email, String password) throws Exception;

     ResponseEntity<Void> logout(String tokenValue);

     ResponseEntity<UserDto> validateToken(String tokenValue) throws Exception;

}
