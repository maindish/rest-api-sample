package com.maindish.restapisample.mockup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maindish.restapisample.common.enums.ServiceCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockupControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void index_get_success() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"))
                .andDo(print());
    }

    @Test
    public void index_get_httpstatus_not_found() throws Exception {
        mockMvc.perform(get("/unknown"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void mockup_get_httpstatus_ok() throws Exception {
        int id = 123;
        String name = "maindish";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("id", Integer.toString(id));
        params.add("name", name);

        mockMvc.perform(get("/mockup").queryParams(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mockup_get_httpstatus_bad_request() throws Exception {
        int id = 123;

        mockMvc.perform(get("/mockup").queryParam("id", Integer.toString(id)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void mockup_post_httpstatus_ok() throws Exception {
        int id = 123;
        String name = "maindish";
        ServiceCode serviceCode = ServiceCode.STEAM;

        ObjectMapper mapper = new ObjectMapper();
        MockupDto mockupDto = MockupDto.builder()
                .age(id)
                .name(name)
                .serviceCode(serviceCode)
                .build();


        String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mockupDto);

        mockMvc.perform(post("/mockup").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mockup_post_httpstatus_bad_request() throws Exception {
        int id = 123;
        String name = " ";
        ServiceCode serviceCode = ServiceCode.STEAM;

        ObjectMapper mapper = new ObjectMapper();
        MockupDto mockupDto = MockupDto.builder()
                .age(id)
                .name(name)
                .serviceCode(serviceCode)
                .build();


        String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mockupDto);

        mockMvc.perform(post("/mockup").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void mockup_post_httpstatus_internal_server_error() throws Exception {
        String requestBody = "TEST";

        mockMvc.perform(post("/mockup").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}

