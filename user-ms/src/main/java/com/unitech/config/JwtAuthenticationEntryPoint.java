package com.unitech.config;

import com.unitech.domain.models.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws AuthenticationException, IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setHeader("access-control-allow-origin", "*");
        httpServletResponse.getWriter().write
                (
                        new ObjectMapper().writeValueAsString(ErrorResponse.instance(HttpStatus.UNAUTHORIZED.value(), "Resursdan istifadə huququnuz yoxdur!"))
                );
    }
}
