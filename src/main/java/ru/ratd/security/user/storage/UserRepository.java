package ru.ratd.security.user.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ratd.security.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
