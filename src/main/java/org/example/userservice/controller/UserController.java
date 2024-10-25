package org.example.userservice.controller;

import org.example.userservice.dto.*;
import org.example.userservice.model.Token;
import org.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();
        String password = signUpRequestDto.getPassword();
        String name = signUpRequestDto.getName();

        return userService.signUp(email,password, name);
    }
    @PostMapping("/login")
    public LogInResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws Exception{
        String email= loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        return userService.login(email,password);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto logOutRequestDto) {
//        Token token = logOutRequestDto.getToken();
        String tokenValue= logOutRequestDto.getTokenValue();
        return userService.logout(tokenValue);
    }

}
