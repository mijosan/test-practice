package com.example.mybatis.user;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest
public class UserControllerTest {
    
    MockMvc mockMvc;

    @MockBean // Mock Bean은 Mock과 달리 Container가 관리하도록 빈을 만듬, 일반적으로 MockMvc와 많이씀
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext ctx;

    UserSaveRequestDto userSaveRequestDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 한글 깨짐 처리
            .build();

        userSaveRequestDto = UserSaveRequestDto.builder()
            .userName("test")
            .userPhoneNumber("01026137832")
            .build();
    }

    @DisplayName("MockMvc를 이용한 postUser slice 테스트")
    @Test
    public void postUserTest() throws Exception {
        // given
        given(userService.postUser(userSaveRequestDto)).willReturn(1L); // Controller가 의존하고 있는 Service객체의 행동을 설정 해준다.
        String content = objectMapper.writeValueAsString(userSaveRequestDto); // dto to json

        // when
        ResultActions resultActions = mockMvc.perform(post("/v1/user")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(content));

        // then
        resultActions
            // ResultActions 객체의 andDo, andExpect, andReturn 메서드 사용
            .andDo(result -> {
                if (result.getResolvedException() != null) {
                    result.getResolvedException().printStackTrace();
                }
            })
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.userId").value("1"))
            .andExpect(jsonPath("$.message").value("회원 등록 완료"));
    }

}
