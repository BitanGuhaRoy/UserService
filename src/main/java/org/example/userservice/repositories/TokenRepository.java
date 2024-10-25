package org.example.userservice.repositories;

import org.example.userservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    Optional<Token> findByTokenValue(String tokenValue);
}
