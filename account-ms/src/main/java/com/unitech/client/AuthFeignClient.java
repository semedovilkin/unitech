package com.unitech.client;

import com.unitech.domain.dto.request.AuthTokenRequestDTO;
import com.unitech.domain.dto.response.AuthCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-v1", url = "${feign.client.config.auth.url}")
public interface AuthFeignClient {

    @PostMapping(value = "/check/jwtToken", consumes = MediaType.APPLICATION_JSON_VALUE)
    AuthCredential getCredential(@RequestBody AuthTokenRequestDTO dto);
}
