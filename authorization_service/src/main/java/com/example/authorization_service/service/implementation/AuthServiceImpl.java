package com.example.authorization_service.service.implementation;

import com.example.authorization_service.dto.JwtResponseDto;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.UserInfoDto;
import com.example.authorization_service.entity.User;
import com.example.authorization_service.repository.SessionsRepository;
import com.example.authorization_service.repository.UsersRepository;
import com.example.authorization_service.service.abstraction.AuthService;
import com.example.authorization_service.service.abstraction.HashFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SessionsRepository sessionsRepository;
    private final UsersRepository usersRepository;
    private final HashFunction hashFunction;

    @Override
    public void registerNewUser(RegistrationDto registrationDto) {
        User userToSave = new User();
        userToSave.setUsername(registrationDto.getNickname());
        userToSave.setEmail(registrationDto.getLogin());
        userToSave.setRole(registrationDto.getRole());
        Timestamp now = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        userToSave.setCreatedAt(now);
        userToSave.setUpdatedAt(now);
        userToSave.setPasswordHash(hashFunction.hash(registrationDto.getPassword()));
        usersRepository.save(userToSave);
    }

    @Override
    public JwtResponseDto loginUser(LoginDto loginDto) {
        return null;
    }

    @Override
    public UserInfoDto getInfoByToken(String accessToken) {
        return null;
    }
}
