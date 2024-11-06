package org.example.userservice.services.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice.dto.*;
import org.example.userservice.exception.InvalidTokenException;
import org.example.userservice.exception.UserDoesnotExistsException;
import org.example.userservice.model.Token;
import org.example.userservice.model.User;
import org.example.userservice.repositories.TokenRepository;
import org.example.userservice.repositories.UserRepository;
import org.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenRepository tokenRepository;
    public SignUpResponseDto signUp(String email, String password, String name) {
        Optional<User> user = userRepository.findByEmail(email);
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        if(user.isPresent()){


            signUpResponseDto.setEmail(user.get().getEmail());
            signUpResponseDto.setName(user.get().getUsername());
            signUpResponseDto.setIsEmailVerified(user.get().getIsEmailVerified());
            signUpResponseDto.setRoles(user.get().getRoles());


        }

        else {
            User newUser= new User();
            newUser.setEmail(email);
            newUser.setPassword(bCryptPasswordEncoder.encode(password));
            newUser.setUsername(name);
            newUser.setIsEmailVerified(false);
            newUser.setRoles(new ArrayList<>());
            userRepository.save(newUser);
            signUpResponseDto.setEmail(newUser.getEmail());
            signUpResponseDto.setName(newUser.getUsername());
            signUpResponseDto.setIsEmailVerified(newUser.getIsEmailVerified());
            signUpResponseDto.setRoles(newUser.getRoles());

        }

        return signUpResponseDto;
    }
    public LogInResponseDto login(String email, String password) throws Exception  {

        Optional<User> user = userRepository.findByEmail(email);
        LogInResponseDto logInResponseDto = new LogInResponseDto();

        if(user.isEmpty())
        {
            logInResponseDto.setMessage("User not found");
            throw new UserDoesnotExistsException("User not found");

        }
        else
        {
            User fetchedUser = user.get();
            String fetchedPassword = fetchedUser.getPassword();
            if(bCryptPasswordEncoder.matches(password, fetchedPassword))
            {
                logInResponseDto.setMessage("Login successful");
                Token token = new Token();
                token.setUser(fetchedUser);
                // token.setTokenValue(UUID.randomUUID().toString());
                token.setIsDeleted(false);
                token.setTokenValue(RandomStringUtils.randomAlphanumeric(32));
                LocalDate localDate = LocalDate.now().plusDays(30);
                Date expireAt = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                token.setExpiryDate(expireAt);
                TokenResponseDto tokenResponseDto = Token.tokenDto(token);
                logInResponseDto.setTokenResponseDto(tokenResponseDto);
                tokenRepository.save(token);
            }
            else{
                logInResponseDto.setMessage("Invalid password");
//                throw new InvalidPasswordException();
            }

        }
        return logInResponseDto;
    }
    public ResponseEntity<Void> logout(String tokenValue){
        Optional<Token> optionalToken = tokenRepository.findByTokenValue(tokenValue);
        if(optionalToken.isPresent()){
            Token token = optionalToken.get();
            token.setIsDeleted(true);
            tokenRepository.save(token);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<UserDto> validateToken(String tokenValue) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findByTokenValue(tokenValue);
        if(optionalToken.isPresent()){
            Token token = optionalToken.get();

            if (token.getIsDeleted()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            User user = token.getUser();
            UserDto userDto = UserDto.getUserDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        else {
            throw new InvalidTokenException("Invalid token");
        }
//            return null;
    }
}
