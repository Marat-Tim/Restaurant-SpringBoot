package com.example.authorization_service.service.implementation;

import com.example.authorization_service.domain.AuthException;
import com.example.authorization_service.dto.TokenResponseDto;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.UserInfoDto;
import com.example.authorization_service.entity.Session;
import com.example.authorization_service.entity.User;
import com.example.authorization_service.repository.SessionsRepository;
import com.example.authorization_service.repository.UsersRepository;
import com.example.authorization_service.service.abstraction.AuthService;
import com.example.authorization_service.service.abstraction.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SessionsRepository sessionsRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    public void registerNewUser(RegistrationDto registrationDto) {
        User userToSave = new User();
        userToSave.setUsername(registrationDto.getNickname());
        userToSave.setEmail(registrationDto.getLogin());
        userToSave.setRole(registrationDto.getRole());
        Timestamp now = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        userToSave.setCreatedAt(now);
        userToSave.setUpdatedAt(now);
        userToSave.setPasswordHash(passwordEncoder.encode(registrationDto.getPassword()));
        usersRepository.save(userToSave);
    }

    @Override
    public TokenResponseDto loginUser(LoginDto loginDto) {
        User user = usersRepository.findByEmail(loginDto.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь с таким логином не найден"));
        if (!passwordEncoder.encode(loginDto.getPassword()).equals(user.getPasswordHash())) {
            throw new AuthException("Неправильный пароль");
        }
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setAccessToken(tokenProvider.generateAccessToken(user));
        return tokenResponseDto;
    }

    @Override
    public UserInfoDto getInfoByToken(String accessToken) {
        Session session = sessionsRepository.findByToken(accessToken)
                .orElseThrow(() -> new AuthException("Не получилось найти сессию по токену"));
        User user = session.getUser();
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setNickname(user.getUsername());
        userInfoDto.setLogin(user.getEmail());
        userInfoDto.setRole(user.getRole());
        return userInfoDto;
    }
}
