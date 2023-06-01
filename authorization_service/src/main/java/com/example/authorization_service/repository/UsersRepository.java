package com.example.authorization_service.repository;

import com.example.authorization_service.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}
