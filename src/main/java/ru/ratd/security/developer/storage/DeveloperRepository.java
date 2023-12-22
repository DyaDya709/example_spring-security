package ru.ratd.security.developer.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ratd.security.developer.model.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
