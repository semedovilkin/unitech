package com.unitech.domain.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCredential {
    private boolean success;
    private String pin;
}
