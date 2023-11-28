package com.study.sjicnho.delivery.user;


import lombok.*;

import javax.jws.soap.SOAPBinding;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer userId;
    private String id;
    private String name;
    private String email;
    private String password;
    private String grade;


    //DTO -> Entity
    public User toEntity(){
        return User.builder()
                .userId(userId)
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .grade(grade)
                .build();
    }

    //Entity -> DTO
    public static UserDto createFromEntity(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .grade(user.getGrade())
                .build();
    }
}
