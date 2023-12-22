package ru.ratd.security.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ratd.security.exception.NotFoundException;
import ru.ratd.security.security.model.SecurityUser;
import ru.ratd.security.user.model.User;
import ru.ratd.security.user.storage.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }


}
