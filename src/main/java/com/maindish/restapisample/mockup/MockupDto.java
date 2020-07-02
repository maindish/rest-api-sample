package com.maindish.restapisample.mockup;

import com.maindish.restapisample.common.enums.ServiceCode;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class MockupDto {
    @NotBlank
    private String name;

    @Positive
    private int age;

    @NotNull
    private ServiceCode serviceCode;
}
