package com.maindish.restapisample.common.enums;

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
    SUCCESS(0, "SUCCESS"),
    BAD_REQUEST(1000, "INVALID_PARAMS"),
    INTERNAL_SERVER_ERROR(5000, "INTERNAL_SERVER_ERROR");

    private int code;
    private String message;
}
