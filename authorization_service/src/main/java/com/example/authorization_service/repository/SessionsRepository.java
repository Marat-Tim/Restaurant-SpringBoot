package com.example.authorization_service.repository;

import com.example.authorization_service.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionsRepository extends CrudRepository<Session, Long> {
    Optional<Session> findByToken(String token);
}
