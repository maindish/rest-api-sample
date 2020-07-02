package com.maindish.restapisample.mockup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maindish.restapisample.common.ResponseDto;
import com.maindish.restapisample.common.enums.Result;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.security.InvalidParameterException;

@RestController
@Validated
@Slf4j
public class MockupController {
    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping(path="/mockup", params = {"id", "name"})
    public ResponseEntity<ResponseDto> getMockup(@RequestParam(name="id") int id, @RequestParam(name="name") String name) throws IllegalArgumentException {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("TEST");

        ResponseDto responseDto = ResponseDto.builder().result(Result.SUCCESS).build();

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(path = "/mockup")
    public ResponseEntity<ResponseDto> createMockup(@RequestBody @Valid MockupDto mockupDto) {
        ResponseDto responseDto = ResponseDto.builder()
                .result(Result.SUCCESS)
                .data(mockupDto)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(path = "/mockup")
    public ResponseEntity<Object> updateMockup(@RequestBody @Valid MockupDto mockupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("TEST");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
