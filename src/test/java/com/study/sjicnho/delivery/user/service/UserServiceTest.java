package com.study.sjicnho.delivery.user.service;

import com.study.sjicnho.delivery.user.entity.UserRole;
import com.study.sjicnho.delivery.user.dto.UserDto;
import com.study.sjicnho.delivery.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDto createUser(){
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setName("이혜지");
        userDto.setEmail("jennt222@naver.com");
        userDto.setPassword("Password1234!");
        userDto.setUserRole(UserRole.OWNER);
        return userDto;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest(){
        UserDto userDto = createUser();
        User savedUser = userService.signUp(userDto);
        assertEquals(userDto.getUserId(), savedUser.getUserId());
        assertEquals(userDto.getName(), savedUser.getName());
        assertEquals(userDto.getEmail(), savedUser.getEmail());
        assertEquals(userDto.getPassword(), savedUser.getPassword());
        assertEquals(userDto.getUserRole(), savedUser.getUserRole());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateUserTest(){
        UserDto user1 = createUser();
        UserDto user2 = createUser();
        userService.signUp(user1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            userService.signUp(user2);});

        assertEquals("이미 가입된 회원입니다.", e.getMessage());

    }
}