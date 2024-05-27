package com.study.sjicnho.delivery.user.service;

import com.study.sjicnho.delivery.common.exception.ErrorCode;
import com.study.sjicnho.delivery.user.exception.DuplicateEmailException;
import com.study.sjicnho.delivery.user.exception.NoSuchUserException;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import com.study.sjicnho.delivery.user.dto.UserDto;
import com.study.sjicnho.delivery.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Integer signUp(UserDto userDto) {
        String username = userDto.getEmail();
        if (userRepository.existsByEmail(username)) {
            throw new DuplicateEmailException();
        }

        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User data = userDto.toEntity();

        User savedUser = userRepository.save(data);
        return savedUser.getUserId();

    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(UserDto.createFromEntity(user));
        }

        return dtos;
    }

    public UserDto getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException(ErrorCode.USER_NOT_FOUND));
        return UserDto.createFromEntity(user);
    }

    public void update(Integer id, UserDto dto) {
        userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException(ErrorCode.USER_NOT_FOUND));

        dto.setUserId(id);
        User user = dto.toEntity();
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException(ErrorCode.USER_NOT_FOUND));

        userRepository.deleteById(id);
    }
}
