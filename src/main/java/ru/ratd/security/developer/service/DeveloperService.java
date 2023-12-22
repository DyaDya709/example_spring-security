package ru.ratd.security.developer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ratd.security.developer.model.Developer;
import ru.ratd.security.developer.storage.DeveloperRepository;
import ru.ratd.security.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public Developer getById(Long id) {
        return developerRepository.findById(id).orElseThrow(() -> new NotFoundException("Developer not found"));
    }

    public Developer create(Developer developer) {
        return developerRepository.save(developer);
    }

    public void delete(Long id) {
        developerRepository.deleteById(id);
    }
}
