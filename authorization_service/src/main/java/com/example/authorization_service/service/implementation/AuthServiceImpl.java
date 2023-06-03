package com.example.authorization_service.service.implementation;

import com.example.authorization_service.DateUtils;
import com.example.authorization_service.domain.AuthException;
import com.example.authorization_service.domain.UserInfo;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.TokenResponseDto;
import com.example.authorization_service.dto.UserInfoDto;
import com.example.authorization_service.entity.Session;
import com.example.authorization_service.entity.User;
import com.example.authorization_service.repository.SessionsRepository;
import com.example.authorization_service.repository.UsersRepository;
import com.example.authorization_service.service.abstraction.AuthService;
import com.example.authorization_service.service.abstraction.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SessionsRepository sessionsRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    @Value("${my.security.access-expiration-minutes}")
    private long accessExpirationMinutes;

    @Override
    public void registerNewUser(RegistrationDto registrationDto) {
        User userToSave = new User();
        userToSave.setUsername(registrationDto.getNickname());
        userToSave.setEmail(registrationDto.getLogin());
        userToSave.setRole(registrationDto.getRole());
        userToSave.setPasswordHash(passwordEncoder.encode(registrationDto.getPassword()));
        try {
            usersRepository.save(userToSave);
        } catch (DataAccessException e) {
            throw new AuthException("Не получилось создать пользователя");
        }
    }

    @Override
    public TokenResponseDto loginUser(LoginDto loginDto) {
        User user = usersRepository.findByEmail(loginDto.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь с таким логином не найден"));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
            throw new AuthException("Неправильный пароль");
        }
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        Optional<Session> lastSession =
                sessionsRepository.findByUser(user)
                        .stream().max(Comparator.comparing(Session::getExpiresAt));
        if (lastSession.isPresent() && lastSession.get().getExpiresAt().after(DateUtils.now())) {
            tokenResponseDto.setAccessToken(lastSession.get().getToken());
        } else {
            Session newSession = generateNewSession(user);
            sessionsRepository.save(newSession);
            tokenResponseDto.setAccessToken(newSession.getToken());
        }
        return tokenResponseDto;
    }

    @Override
    public UserInfoDto getInfoByToken(String accessToken) {
        UserInfo userInfo = tokenProvider.getUserInfo(accessToken);
        User user = usersRepository.findById(userInfo.getUserId())
                .orElseThrow(() -> new AuthException("Пользователя с переданным идентификатором не существует"));
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setNickname(user.getUsername());
        userInfoDto.setLogin(user.getEmail());
        userInfoDto.setRole(user.getRole());
        userInfoDto.setCreatedAt(user.getCreatedAt().toLocalDateTime());
        return userInfoDto;
    }

    private Session generateNewSession(User user) {
        Session newSession = new Session();
        newSession.setUser(user);
        Date accessExpiration = DateUtils.nowPlusMinutes(accessExpirationMinutes);
        newSession.setExpiresAt(new Timestamp(accessExpiration.getTime()));
        newSession.setToken(tokenProvider.generateAccessToken(user, accessExpiration));
        return newSession;
    }
}
