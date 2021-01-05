package com.example.mybatis.user;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.mybatis.dto.UserSaveRequestDto;
import com.example.mybatis.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest
public class UserControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    UserSaveRequestDto userSaveRequestDto;

    @BeforeEach
    void setUp() {
        userSaveRequestDto = UserSaveRequestDto.builder()
            .userName("test")
            .userPhoneNumber("01026137832")
            .build();
    }

    @DisplayName("MockMvc를 이용한 postUser slice 테스트")
    @Test
    public void postUserTest() throws Exception {
        // given
        given(userService.insertUser(userSaveRequestDto)).willReturn(1L);
        String content = objectMapper.writeValueAsString(userSaveRequestDto);

        // when
        MvcResult mvcResult = mockMvc.perform(post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                            .andExpect(status().isOk())
                            .andReturn();
        
        // then
        String result = mvcResult.getResponse().getContentAsString();

        System.out.println(result);
    }

}
