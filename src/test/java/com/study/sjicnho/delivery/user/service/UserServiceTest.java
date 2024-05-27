package com.study.sjicnho.delivery.user.service;

import com.study.sjicnho.delivery.common.exception.ErrorCode;
import com.study.sjicnho.delivery.user.dto.UserDto;
import com.study.sjicnho.delivery.user.entity.User;
import com.study.sjicnho.delivery.user.entity.UserRole;
import com.study.sjicnho.delivery.user.exception.DuplicateEmailException;
import com.study.sjicnho.delivery.user.exception.NoSuchUserException;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 성공")
    void testSignUp_Success() {
        // Given
        UserDto userDto = new UserDto(1, "홍길동", "user1234@naver.com", "Password123!", UserRole.CUSTOMER);
        User user = userDto.toEntity();

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        Integer userId = userService.signUp(userDto);

        // Then
        assertNotNull(userId);
        assertEquals(1, userId);
    }

    @Test
    @DisplayName("회원가입 중복 이메일 테스트")
    void testSignUp_EmailAlreadyExists() {
        // Given
        UserDto userDto = new UserDto(1, "홍길동", "user1234@naver.com", "Password123!", UserRole.CUSTOMER);
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // When & Then
        DuplicateEmailException exception = assertThrows(DuplicateEmailException.class, () -> userService.signUp(userDto));
        assertEquals(ErrorCode.DUPLICATE_EMAIL, exception.getErrorCode());
    }

    @Test
    @DisplayName("회원 정보 모두 가져오기 성공")
    void testGetAll_Success() {
        // Given
        User user1 = new User(1, "홍길동", "user1234@naver.com", "Password123!", UserRole.CUSTOMER);
        User user2 = new User(2, "김도운", "user2345@naver.com", "Password123!@", UserRole.OWNER);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // When
        List<UserDto> result = userService.getAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());

        UserDto userDto1 = result.get(0);
        assertEquals(1, userDto1.getUserId());
        assertEquals("user1234@naver.com", userDto1.getEmail());
        assertEquals("홍길동", userDto1.getName());
        assertEquals(UserRole.CUSTOMER, userDto1.getUserRole());

        UserDto userDto2 = result.get(1);
        assertEquals(2, userDto2.getUserId());
        assertEquals("user2345@naver.com", userDto2.getEmail());
        assertEquals("김도운", userDto2.getName());
        assertEquals(UserRole.OWNER, userDto2.getUserRole());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Id 값으로 회원 정보 가져오기 성공")
    void testGetById_Success() {
        // Given
        User user = new User(1, "홍길동", "user1234@naver.com", "Password123!", UserRole.CUSTOMER);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // When
        UserDto result = userService.getById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("user1234@naver.com", result.getEmail());
        assertEquals("홍길동", result.getName());
        assertEquals(UserRole.CUSTOMER, result.getUserRole());

        verify(userRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("회원 정보 가져오기 실패")
    void testGetById_UserNotFound() {
        // Given
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        NoSuchUserException exception = assertThrows(NoSuchUserException.class, () -> userService.getById(1));
        assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());

        verify(userRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void testUpdate_Success() {
        // Given
        User existingUser =  new User(1, "홍길동", "user1234@naver.com", "Password123!", UserRole.CUSTOMER);

        UserDto updateDto = new UserDto();
        updateDto.setEmail("updated@example.com");
        updateDto.setPassword("newPassword!");
        updateDto.setName("홍수정");
        updateDto.setUserRole(UserRole.CUSTOMER);

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        // When
        userService.update(1, updateDto);

        // Then
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("회원 정보 수정 실패")
    void testUpdate_UserNotFound() {
        // Given
        UserDto updateDto = new UserDto();
        updateDto.setEmail("updated@example.com");
        updateDto.setPassword("newPassword");
        updateDto.setName("홍수정");
        updateDto.setUserRole(UserRole.CUSTOMER);

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        NoSuchUserException exception = assertThrows(NoSuchUserException.class, () -> userService.update(1, updateDto));
        assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    @DisplayName("회원 정보 삭제 성공")
    void testDelete_Success() {
        // Given
        User existingUser = new User(1, "홍길동", "user1234@naver.com", "Password123!", UserRole.CUSTOMER);

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        // When
        userService.delete(1);

        // Then
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("회원 정보 삭제 실패")
    void testDelete_UserNotFound() {
        // Given
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        NoSuchUserException exception = assertThrows(NoSuchUserException.class, () -> userService.delete(1));
        assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(0)).deleteById(1);
    }
}
