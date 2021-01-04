package com.example.mybatis.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    UserMapper userMapper;

    @InjectMocks // Mock 객체가 생성 되는가 ?
    UserServiceImpl userService;

    UserSaveRequestDto userSaveRequestDto;

    UserResponseDto userResponseDto;

    @BeforeEach
    void setUp() {
        userSaveRequestDto = UserSaveRequestDto.builder()
            .userName("test")
            .userPhoneNumber("01026137832")
            .build();
        
        userResponseDto = UserResponseDto.builder()
            .userId(1L)
            .userName("test")
            .userPhoneNumber("01026137832")
            .build();
    }

    // Mockito 이용한 테스트 코드
    @DisplayName("Mock을 사용한 insertUser 테스트")
    @Test
    public void insertUser() {
        // given
        given(userMapper.insertUser(userSaveRequestDto)).willReturn(1L);
        given(userMapper.selectUser(any())).willReturn(userResponseDto);

        // when
        Long userId = userService.insertUser(userSaveRequestDto);
        UserResponseDto userResponseDto2 = userService.selectUser(userId);

        // then
        then(userMapper).should().insertUser(userSaveRequestDto);
        then(userMapper).should().selectUser(any());

        assertThat(userId).isEqualTo(userResponseDto2.getUserId());
        assertThat(userSaveRequestDto.getUserName()).isEqualTo(userResponseDto2.getUserName());
        assertThat(userSaveRequestDto.getUserPhoneNumber()).isEqualTo(userResponseDto2.getUserPhoneNumber());
    }

}
