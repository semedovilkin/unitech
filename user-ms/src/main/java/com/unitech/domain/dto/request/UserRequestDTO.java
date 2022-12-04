package com.unitech.domain.dto.request;


import com.unitech.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    @NotEmpty(message = "Pin must be provided")
    private String pin;

    @NotEmpty(message = "Password must be provided")
    @Pattern(regexp = Constants.VALID_PASSWORD_REGEXP,
            message = Constants.VALID_PASSWORD_MESSAGE)
    private String password;
}
