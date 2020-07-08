package com.maindish.restapisample.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
//@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Result {
    SUCCESS(0, "success"),
    BAD_REQUEST(1000, "invalid request"),
    INTERNAL_SERVER_ERROR(5000, "server error");

    private int code;
    private String message;

    @JsonCreator
    public static Result fromValue(int code, String message) {
        return valueToEnum(code, message);
    }

    private static Result valueToEnum(int code, String message) {
        for (Result result : values()) {
            if ((result.getCode() == code) && result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
