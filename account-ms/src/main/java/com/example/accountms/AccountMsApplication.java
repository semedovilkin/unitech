package com.example.accountms;

import com.example.accountms.domain.entity.User;
import com.example.accountms.domain.entity.UserAccount;
import com.example.accountms.domain.enumeration.AccountStatusEnum;
import com.example.accountms.domain.enumeration.UserStatusesEnum;
import com.example.accountms.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootApplication
@EnableFeignClients
public class AccountMsApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccountMsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User().setPin("63RLF5Z")
                .setStatus(UserStatusesEnum.ACTIVE)
                .setAccounts(Collections.singletonList(
                        new UserAccount()
                                .setAccountNo("123")
                                .setBalance(new BigDecimal(150))
                                .setStatus(AccountStatusEnum.ACTIVE)
                ));
                userRepository.save(user);
    }
}
