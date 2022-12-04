package com.unitech.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomFieldErrorResponse {
    public String message;
    public String field;
}