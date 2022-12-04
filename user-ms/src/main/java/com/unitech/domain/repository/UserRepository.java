package com.unitech.domain.repository;

import com.unitech.domain.entity.User;
import com.unitech.domain.enumeration.UserStatusesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select a from User a where a.pin=:pin and not a.status=:status")
    User findByPin(
            @Param("pin") String pin,
            @Param("status") UserStatusesEnum accountStatusExcluded
    );
}
