package com.example.accountms.domain.repository;

import com.example.accountms.domain.entity.User;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Where(clause = "state <> 0")
    User findByPin(String pin);
}
