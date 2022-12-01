package com.example.accountms.domain.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCredential {
    private boolean success;
    private String pin;

    public AuthCredential(boolean success) {
        this.success = success;
    }
}
