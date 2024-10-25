package org.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Role extends  BaseModel{

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    private String name;
}
