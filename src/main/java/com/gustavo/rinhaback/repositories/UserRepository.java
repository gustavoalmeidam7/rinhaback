package com.gustavo.rinhaback.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.rinhaback.domain.user.*;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUserIndexContainingIgnoreCase(String userIndex, Pageable pageable);
}
