package com.example.accountms.client;

import com.example.accountms.domain.dto.request.AuthTokenRequestDTO;
import com.example.accountms.domain.dto.response.AuthCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-v1", url = "${feign.client.config.auth.url}")
public interface AuthFeignClient {

    @PostMapping(value = "/check/jwtToken", consumes = MediaType.APPLICATION_JSON_VALUE)
    AuthCredential getCredential(@RequestBody AuthTokenRequestDTO dto);
}
