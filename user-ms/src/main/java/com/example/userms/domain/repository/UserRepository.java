package com.example.userms.domain.repository;

import com.example.userms.domain.entity.User;
import com.example.userms.domain.enumeration.UserStatusesEnum;
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

//
//    @Modifying
//    @Query(
//            "update User a set a.status=:status where a.id=:id"
//    )
//    Integer updateStatus(
//            @Param("id") Long id,
//            @Param("status") UserStatusesEnum accountStatus
//    );
//
//    @Query(
//            "from User a where (a.email=:emailOrUsername or a.username=:emailOrUsername) " +
//                    "and a.password=:password and a.status=:status"
//    )
//    User findOneByUsernameOrEmailAndPassword(
//            @Param("emailOrUsername") String emailOrUsername,
//            @Param("password") String password,
//            @Param("status") UserStatusesEnum accountStatus
//    );
//
//    @Query(
//            "from User a where (a.email=:emailOrUsername or a.username=:emailOrUsername) " +
//                    " and a.status=:status"
//    )
//    User findOneByUsernameOrEmail(
//            @Param("emailOrUsername") String emailOrUsername,
//            @Param("status") UserStatusesEnum accountStatus
//    );
}
