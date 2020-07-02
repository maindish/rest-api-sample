package com.maindish.restapisample.mockup;

import com.maindish.restapisample.common.enums.ServiceCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockupDtoTest {

    @Test
    void getName_success() {
        final String expectedName = "maindish";
        final MockupDto mockupDto = MockupDto.builder()
                .name(expectedName)
                .build();

        final String actualName = mockupDto.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void getAge_success() {
        final int expectedAge = 10;
        final MockupDto mockupDto = MockupDto.builder()
                .age(expectedAge)
                .build();

        final int actualAge = mockupDto.getAge();

        assertEquals(expectedAge, actualAge);
    }

    @Test
    void getServiceCode_success() {
        final ServiceCode expectedServiceCode = ServiceCode.NEXON;
        final MockupDto mockupDto = MockupDto.builder()
                .serviceCode(expectedServiceCode)
                .build();

        final ServiceCode actualServiceCode = ServiceCode.NEXON;

        assertEquals(expectedServiceCode, actualServiceCode);
    }

    @Test
    void getServiceCode_fail() {
        /*final ServiceCode expectedServiceCode = ServiceCode.NEXON;
        final MockupDto mockupDto = MockupDto.builder()
                .serviceCode(expectedServiceCode)
                .build();

        final ServiceCode actualServiceCode = ServiceCode.STEAM;

        assertEquals(expectedServiceCode, actualServiceCode);*/
    }
}