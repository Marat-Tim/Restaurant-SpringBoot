package com.example.authorization_service.repository;

import com.example.authorization_service.entity.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionsRepository extends CrudRepository<Session, Long> {
}
