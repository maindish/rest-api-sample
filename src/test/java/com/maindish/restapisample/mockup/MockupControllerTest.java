package com.maindish.restapisample.mockup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maindish.restapisample.common.enums.ServiceCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MockupControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void index_get_http_status_ok() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    public void index_get_http_status_not_found() throws Exception {
        mockMvc.perform(get("/unknown"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void mockup_get_http_status_ok() throws Exception {
        final int id = 123;
        final String name = "maindish";

        mockMvc.perform(
                get("/mockup")
                        .param("id", String.valueOf(id))
                        .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mockup_get_httpstatus_bad_request() throws Exception {
        final int id = 123;

        mockMvc.perform(
                get("/mockup")
                        .param("id", String.valueOf(id)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void mockup_post_httpstatus_ok() throws Exception {
        final int age = 123;
        final String name = "maindish";
        final ServiceCode serviceCode = ServiceCode.STEAM;
        final int expectedServiceCode = 1;

        ObjectMapper mapper = new ObjectMapper();
        MockupDto mockupDto = MockupDto.builder()
                .age(age)
                .name(name)
                .serviceCode(serviceCode)
                .build();

        String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mockupDto);

        mockMvc.perform(
                post("/mockup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(name)))
                .andExpect(jsonPath("$.data.age", is(age)))
                .andExpect(jsonPath("$.data.serviceCode", is(expectedServiceCode)));
    }

    @Test
    public void mockup_post_httpstatus_bad_request() throws Exception {
        final int age = 123;
        final String name = " ";
        final ServiceCode serviceCode = ServiceCode.STEAM;

        ObjectMapper mapper = new ObjectMapper();
        MockupDto mockupDto = MockupDto.builder()
                .age(age)
                .name(name)
                .serviceCode(serviceCode)
                .build();

        String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mockupDto);

        mockMvc.perform(
                post("/mockup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void mockup_post_httpstatus_internal_server_error() throws Exception {
        String requestBody = "TEST";

        mockMvc.perform(
                post("/mockup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}

