package com.example.accountms.domain.repository;

import com.example.accountms.domain.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "accounts")
    User findByPin(String pin);
}
