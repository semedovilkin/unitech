package com.example.accountms;

import com.example.accountms.domain.entity.User;
import com.example.accountms.domain.entity.UserAccount;
import com.example.accountms.domain.enumeration.AccountStatusEnum;
import com.example.accountms.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

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

//        User user = new User()
//                .setPin("63RLF5Z");
//
//        List<UserAccount> userAccounts = Collections.singletonList(
//                new UserAccount()
//                        .setAccountNo("123")
//                        .setBalance(new BigDecimal(150))
//                        .setStatus(AccountStatusEnum.ACTIVE)
//                        .setUser(user));
//
//        user.setAccounts(userAccounts);
//        User save = userRepository.save(user);

//        User user1 = new User().setId(3).setPin("aaaaa");
//
//        userRepository.save(user1);

    }
}
