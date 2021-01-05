package com.example.mybatis.user;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    UserMapper userMapper;

    @InjectMocks
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

        // when
        Long userId = userService.insertUser(userSaveRequestDto);

        // then
        ArgumentCaptor<UserSaveRequestDto> captor = ArgumentCaptor.forClass(UserSaveRequestDto.class);

        then(userMapper).should().insertUser(captor.capture()); // 검증 1.userMapper가 insertUser를 호출했는지 검증
        assertThat(captor.getValue()).isEqualTo(userSaveRequestDto);  // 검증 2.매개변수가 inserUser의 매개변수로 잘들어 가였는지 검증
        assertThat(userId).isEqualTo(1L); // 검증 3.userMapper에서 반환한 값과 userService 객체에서 반환한 값과 같은지 검증
    }

}
