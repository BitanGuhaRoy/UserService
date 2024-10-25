package org.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.model.Token;

@Getter
@Setter
public class LogOutRequestDto {
    private String tokenValue;
}
