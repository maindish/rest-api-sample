package com.maindish.restapisample.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ServiceCode {
    STEAM(1),
    NEXON(2),
    ORIGIN(3);

    private int code;

    @JsonCreator
    public static ServiceCode fromInt(int code) {
        return intToEnum(code);
    }

    @JsonValue
    public int getCode() {
        return this.code;
    }

    private static final ServiceCode intToEnum(int code) {
        for (ServiceCode serviceCode : values()) {
            if (serviceCode.getCode() == code) {
                return serviceCode;
            }
        }
        return null;
    }
}
