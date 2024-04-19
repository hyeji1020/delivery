package com.study.sjicnho.delivery.user.service;

import com.study.sjicnho.delivery.ErrorCode;
import com.study.sjicnho.delivery.store.exception.NoSuchStore;
import com.study.sjicnho.delivery.user.entity.UserRole;
import com.study.sjicnho.delivery.user.exception.NoSuchUser;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import com.study.sjicnho.delivery.user.dto.UserDto;
import com.study.sjicnho.delivery.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

    public User signUp(UserDto userDto) {

        String username = userDto.getEmail();
        String password = userDto.getPassword();
        String name = userDto.getName();
        UserRole role = userDto.getUserRole();

        Boolean isExist = userRepository.existsByEmail(username);

        if (isExist) {
            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
        }

        userDto.setEmail(username);
        userDto.setPassword(bCryptPasswordEncoder.encode(password));
        userDto.setUserRole(role);
        userDto.setName(name);
        User data = userDto.toEntity();

        return userRepository.save(data);
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUser(ErrorCode.USER_NOT_FOUND));
    }

    public void update(Integer id, UserDto dto) {

        dto.setUserId(id);

        //DTO->Entity
        User user = dto.toEntity();

        User target = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUser(ErrorCode.USER_NOT_FOUND));

        if(target != null){
            userRepository.save(user);
        }
    }

    public void delete(Integer id) {

        User target = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUser(ErrorCode.USER_NOT_FOUND));
        if(target != null){
            userRepository.deleteById(id);
        }
    }
}
