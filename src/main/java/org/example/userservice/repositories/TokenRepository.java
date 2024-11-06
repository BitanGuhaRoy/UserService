package org.example.userservice.repositories;

import org.example.userservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

//    @Query("SELECT t FROM Token t WHERE t.tokenValue = :tokenValue")
    Optional<Token> findByTokenValue(String tokenValue);

}
