package com.maindish.restapisample.mockup;

import com.maindish.restapisample.common.ResponseDto;
import com.maindish.restapisample.common.enums.Result;
import com.maindish.restapisample.common.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes,
                                 ServerProperties serverProperties,
                                 List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        log(request);

        ResponseDto responseDto = ResponseDto.builder().result(Result.INTERNAL_SERVER_ERROR).error(new Error("TEST")).build();
        Map<String, Object> responseDtoMap = new HashMap<String, Object>();

        responseDtoMap.put("test", responseDto);

        return new ResponseEntity<Map<String, Object>>(responseDtoMap, HttpStatus.NOT_FOUND);
        //return super.error(request);
    }

    private void log(HttpServletRequest request) {
        log.error("error");
        log.error(request.toString());
    }
}
