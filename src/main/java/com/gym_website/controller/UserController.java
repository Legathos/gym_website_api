package com.gym_website.controller;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gym_website.service.UserService;
import com.gym_website.service.utils.ResponseUtilService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ResponseUtilService responseUtilService;
    public UserController(UserService userService, ResponseUtilService responseUtilService) {
        this.userService = userService;
        this.responseUtilService = responseUtilService;
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto) {
        ResponseDto responseDto = userService.register(userDto);
        return responseUtilService.sendResponse(responseDto);
    }

}
