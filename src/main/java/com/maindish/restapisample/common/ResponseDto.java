package com.maindish.restapisample.common;

import com.maindish.restapisample.common.enums.Result;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ResponseDto {
    private Result result;
    private Error error;
    private Object data;
}
