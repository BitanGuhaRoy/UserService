package org.example.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends  BaseModel{
    private String username;
    private String password;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private Boolean isEmailVerified;


}
