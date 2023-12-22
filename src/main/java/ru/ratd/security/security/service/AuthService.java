package ru.ratd.security.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ratd.security.exception.JwtAuthenticationException;
import ru.ratd.security.security.model.AuthenticationRequestDTO;
import ru.ratd.security.security.token.JwtTokenProvider;
import ru.ratd.security.user.model.User;
import ru.ratd.security.user.storage.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManagerBean;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Map<Object, Object> authenticate(AuthenticationRequestDTO request) {
        try {
            authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
//            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
//            // Получите хешированный пароль из базы данных
//            String hashedPasswordFromDB = user.getPassword();
//
//            // Хэшируем введенный пользователем пароль и сравниваем с хешем из базы
//            if (!passwordEncoder.matches(request.getPassword(), hashedPasswordFromDB)) {
//                throw new JwtAuthenticationException("Invalid email/password combination", HttpStatus.FORBIDDEN);
//            }
            return response;
        } catch (AuthenticationException e) {
            throw new JwtAuthenticationException("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
