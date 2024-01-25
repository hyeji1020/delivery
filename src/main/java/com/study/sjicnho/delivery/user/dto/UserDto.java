package com.study.sjicnho.delivery.user.dto;

import com.study.sjicnho.delivery.user.RoleType;
import com.study.sjicnho.delivery.user.entity.User;
import lombok.*;

import javax.jws.soap.SOAPBinding;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer userId;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일은 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    private RoleType roleType;


    //DTO -> Entity
    public User toEntity(){
        return User.builder()
                .userId(userId)
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .roleType(roleType)
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
                .roleType(user.getRoleType())
                .build();
    }
}