package org.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.userservice.dto.TokenResponseDto;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    @ManyToOne
    private User user;
    private String tokenValue;
    private Date expiryDate;
    public static TokenResponseDto tokenDto(Token token){

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setTokenValue(token.getTokenValue());
        tokenResponseDto.setExpiryDate(token.getExpiryDate());
        return tokenResponseDto;
    }
}
