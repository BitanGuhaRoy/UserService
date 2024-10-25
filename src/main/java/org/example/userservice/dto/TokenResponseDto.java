package org.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TokenResponseDto {
    public String tokenValue;
    public Date expiryDate;
}
