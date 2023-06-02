package com.example.authorization_service.repository;

import com.example.authorization_service.entity.Session;
import com.example.authorization_service.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionsRepository extends CrudRepository<Session, Long> {
    List<Session> findByUser(User user);
}
