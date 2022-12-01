package com.example.userms.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthCredential {
    private boolean success;
    private String pin;

    public AuthCredential(boolean success) {
        this.success = success;
    }
}
