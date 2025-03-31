package com.gustavo.rinhaback.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gustavo.rinhaback.domain.user.User;
import com.gustavo.rinhaback.domain.user.UserDTO;
import com.gustavo.rinhaback.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(UUID id) {
        return repository.findById(id);
    }

    public List<User> getByTerm(String term) {
        Pageable pgReq = PageRequest.of(0, 50);

        return repository.findByUserIndexContainingIgnoreCase(term, pgReq);
    }

    public long getNumberUsers() {
        return repository.count();
    }

    public User save(UserDTO user) {
        User userEntity = repository.save(user.toEntity());
        return userEntity;
    }


    public void deleteAll() {
        repository.deleteAll();
    }
}
