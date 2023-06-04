package com.example.authorization_service.repository;

import com.example.authorization_service.entity.Session;
import com.example.authorization_service.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface SessionsRepository extends CrudRepository<Session, Long> {
    List<Session> findByUser(User user);

    default Optional<Session> lastSessionForUser(User user) {
        return findByUser(user).stream().max(Comparator.comparing(Session::getExpiresAt));
    }
}
