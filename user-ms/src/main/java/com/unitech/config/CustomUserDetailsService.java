package com.unitech.config;

import com.unitech.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public CustomUserDetailsService(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        String pin = jwtTokenProvider.getPinFromJWT(token);
        com.unitech.domain.entity.User user = userService.getUserByPin(pin);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by pin: " + pin);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getName().toString()));
        });

        return new User(pin, user.getPassword(), authorities);
    }


}

