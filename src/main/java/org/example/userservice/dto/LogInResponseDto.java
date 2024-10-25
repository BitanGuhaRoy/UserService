package org.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.model.Token;
@Setter
@Getter
public class LogInResponseDto {
    private String message;
    private TokenResponseDto tokenResponseDto;
}
