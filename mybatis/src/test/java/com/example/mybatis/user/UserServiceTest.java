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

    
    @DisplayName("Mock을 사용한 getUser 테스트")
    @Test
    public void getUser() {
        Long userId = 1L;

        // given
        given(userMapper.selectUser(userId)).willReturn(userResponseDto);

        // when
        UserResponseDto userResponseDto2 = userService.getUser(userId);

        // then
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        then(userMapper).should().selectUser(captor.capture()); // 검증 1.userMapper.selectUser메서드를 호출하는지

        assertThat(captor.getValue()).isEqualTo(userId); // 검증 2.userMapper.selectUser메서드의 매개변수로 userId가 잘 전달 되었는지
        assertThat(userResponseDto2.getUserName()).isEqualTo("test");
        assertThat(userResponseDto2.getUserPhoneNumber()).isEqualTo("01026137832");
    }

    @DisplayName("Mock을 사용한 postUser 테스트")
    @Test
    public void postUser() {
        // given
        given(userMapper.selectMaxUserId()).willReturn(1L);

        // when
        Long userId = userService.postUser(userSaveRequestDto);

        // then
        ArgumentCaptor<UserSaveRequestDto> captor = ArgumentCaptor.forClass(UserSaveRequestDto.class);
        then(userMapper).should().insertUser(captor.capture()); // 검증 1.userMapper가 insertUser를 호출했는지 검증

        then(userMapper).should().selectMaxUserId(); // 검증 2.userMapper가 selectMasxuserId를 호출했는지 검증

        assertThat(captor.getValue()).isEqualTo(userSaveRequestDto);  // 검증 3.매개변수가 inserUser의 매개변수로 잘들어 가였는지 검증
        assertThat(userId).isEqualTo(1L); // 검증 4.userMapper에서 반환한 값과 userService 객체에서 반환한 값과 같은지 검증
    }

}
