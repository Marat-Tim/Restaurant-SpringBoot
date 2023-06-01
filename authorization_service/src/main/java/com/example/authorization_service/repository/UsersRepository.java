package com.example.authorization_service.repository;

import com.example.authorization_service.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
