package com.study.sjicnho.delivery.user.service;

import com.study.sjicnho.delivery.user.RoleType;
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

    public void signUpProcess(UserDto userDto) {

        String username = userDto.getEmail();
        String password = userDto.getPassword();
        RoleType role = userDto.getRoleType();

        Boolean isExist = userRepository.existsByEmail(username);

        if (isExist) {
            throw new IllegalStateException("이미 가입되어 있는 이메일입니다.");
        }

        userDto.setEmail(username);
        userDto.setPassword(bCryptPasswordEncoder.encode(password));
        userDto.setRoleType(role);
        User data = userDto.toEntity();

        userRepository.save(data);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Boolean isExist = userRepository.existsByUserEmail(email);
//
//        if (!isExist) {
//            throw new UsernameNotFoundException(email);
//        }
//        log.info("{user} :", isExist);
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getEmail())
//                .password(user.getPassword())
//                .roles(user.getRoleType().toString())
//                .build();
//
//    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

//    public User getByEmail(String email){
//        return userRepository.findByUserEmail(email);
//    }
//
//    public User signUp(UserDto userDto) {
//
//        //비밀번호 암호화
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        //DTO->Entity
//        User user = userDto.toEntity();
//
//        validDuplicateUser(user);
//
//        return userRepository.save(user);
//    }

//    //중복 회원 유효성 검사
//    private void validDuplicateUser(User user){
//        User findUser = userRepository.findByUserEmail(user.getEmail());
//        if(findUser != null){
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }
//    }

    public void update(Integer id, UserDto dto) {

        dto.setUserId(id);

        //DTO->Entity
        User user = dto.toEntity();

        User target = userRepository.findById(id).orElseThrow(null);
        if(target != null){
            userRepository.save(user);
        }
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

//    public User login(UserDto userDto) {
//
//        String email = userDto.getEmail();
//        log.info("{email}:", email);
//
//        User user = userRepository.findByEmail(userDto.getEmail());
//        log.info("{user}:", user);
//
//        if (!userDto.getEmail().equals(user.getEmail())) {
//            throw new IllegalStateException("이메일을 확인해주세요");
//        } else {
//            return user;
//        }
//
//    }

}
