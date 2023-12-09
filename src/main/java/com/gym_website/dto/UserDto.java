package com.gym_website.dto;

import lombok.Data;
@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String role;
    private Long age;
    private Long weight;
    private Long height;
    private String gender;
}
