package com.maindish.restapisample.mockup;

import com.maindish.restapisample.common.ResponseDto;
import com.maindish.restapisample.common.enums.Result;
import com.maindish.restapisample.common.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

//@RestControllerAdvice(basePackages = {"com.maindish.restapisample.mockup"})
@RestControllerAdvice
public class MockupControllerAdvice {
    private ResponseEntity<ResponseDto> responseEntity = null;

    @ExceptionHandler({
            UnsatisfiedServletRequestParameterException.class,
            IllegalArgumentException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResponseDto> badRequestException(HttpServletRequest request, Exception e) {
        ResponseDto responseDto = ResponseDto.builder().result(Result.BAD_REQUEST).error(new Error(e)).build();
        responseEntity = new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

        return responseEntity;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ResponseDto> defaultException(HttpServletRequest request, Exception e) {
        ResponseDto responseDto = ResponseDto.builder().result(Result.INTERNAL_SERVER_ERROR).error(new Error(e)).build();
        responseEntity = new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }
}
