package com.unitech.domain.enumeration;

public enum EntityStateEnum {
    DELETED(0),
    ACTIVE(1),
    UPDATED(2);

    private Integer code;

    EntityStateEnum(int code) {
        this.code = code;
    }
}
