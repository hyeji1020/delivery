package com.study.sjicnho.delivery.user;

import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User create(UserDto userDto) {

        //DTO->Entity
        User user = userDto.toEntity();

        return userRepository.save(user);
    }

    public void update(Integer id, UserDto dto) {

        //DTO->Entity
        User user = dto.toEntity();

        User getUser = userRepository.findById(id).orElse(null);
        if(getUser != null){
            userRepository.save(user);
        }
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
