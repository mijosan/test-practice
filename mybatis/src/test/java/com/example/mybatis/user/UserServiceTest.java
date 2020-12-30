package com.example.mybatis.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.service.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @DisplayName("insertUser 테스트")
    @Test
    public void insertUser() {
        // given
        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
            .userName("test")
            .userPhoneNumber("01026137832")
            .build();
        
        // when
        Long userId = userService.insertUser(userSaveRequestDto);

        // then
        assertThat(userId).isEqualTo(1L);

    }

    @DisplayName("userMapper를 이용한 유저 저장 테스트")
    @Transactional
    @Test
    public void mapper_insertUser() {
        // given
        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
            .userName("test")
            .userPhoneNumber("01026137832")
            .build();

        userMapper.insertUser(userSaveRequestDto);

        // when
        List<UserResponseDto> userResponseDtoList = userMapper.selectUserList();

        // then
        UserResponseDto userResponseDto = userResponseDtoList.get(0);
        assertThat(userResponseDto.getUserName()).isEqualTo("test");
        assertThat(userResponseDto.getUserPhoneNumber()).isEqualTo("01026137832");
    }


}
