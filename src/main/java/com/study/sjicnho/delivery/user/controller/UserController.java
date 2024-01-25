package com.study.sjicnho.delivery.user.controller;

import com.study.sjicnho.delivery.user.service.UserService;
import com.study.sjicnho.delivery.user.dto.UserDto;
import com.study.sjicnho.delivery.user.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
         return userService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody UserDto userDto){
        userService.create(userDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody UserDto userDto){
        userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userService.delete(id);
    }
}
