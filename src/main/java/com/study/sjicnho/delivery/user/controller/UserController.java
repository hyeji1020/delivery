package com.study.sjicnho.delivery.user.controller;

import com.study.sjicnho.delivery.user.service.UserService;
import com.study.sjicnho.delivery.user.dto.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id){
         return userService.getById(id);
    }

    //회원가입
    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody UserDto userDto){
            userService.signUp(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
    public void update(@PathVariable Integer id,@Valid @RequestBody UserDto userDto){
        userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
    public void delete(@PathVariable Integer id){
        userService.delete(id);
    }

}
