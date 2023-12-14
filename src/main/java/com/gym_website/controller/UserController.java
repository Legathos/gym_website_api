package com.gym_website.controller;

import com.gym_website.config.JwtTokenService;
import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
import com.gym_website.dto.UserLoginDto;
import com.gym_website.dto.UserWeightDto;
import com.gym_website.service.utils.LoginUtilService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.gym_website.service.UserService;
import com.gym_website.service.utils.ResponseUtilService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ResponseUtilService responseUtilService;
    private final JwtTokenService jwtTokenService;
    private final LoginUtilService loginUtilService;
    public UserController(UserService userService, ResponseUtilService responseUtilService, JwtTokenService jwtTokenService, LoginUtilService loginUtilService) {
        this.userService = userService;
        this.responseUtilService = responseUtilService;
        this.jwtTokenService = jwtTokenService;
        this.loginUtilService = loginUtilService;
    }



    @PostMapping("login")
    @SneakyThrows
        public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDto, HttpServletResponse httpServletResponse) {
        String jwt = "";
        UserDto userDto = userService.loginUser(userLoginDto);
        if (userDto != null) {
            jwt = jwtTokenService.createJwtToken(userLoginDto.getUsername(), LoginUtilService.getRole(userDto));
            httpServletResponse.addCookie(LoginUtilService.createJwtCookie(jwt));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("register")
    @SneakyThrows
    public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto) {
        ResponseDto responseDto = userService.register(userDto);
        return responseUtilService.sendResponse(responseDto);
    }

    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    @GetMapping("get-user-info-{id}")
    public ResponseEntity<UserDto> getUserInfoById (@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.getUserInfoById(id));
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    @DeleteMapping("delete-user-{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    @PutMapping("edit-user")
    public ResponseEntity<ResponseDto> editUser(@RequestBody UserDto userDto) {
        ResponseDto responseDto = userService.editUser(userDto);
        return responseUtilService.sendResponse(responseDto);
    }

    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    @PostMapping("update-user-weight")
    public ResponseEntity<ResponseDto> updateWeight(@RequestBody UserWeightDto userWeightDto){
        ResponseDto responseDto = userService.updateUserWeight(userWeightDto);
        return responseUtilService.sendResponse(responseDto);
    }

    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    @GetMapping("weight-history-user-{id}")
    public ResponseEntity<List<UserWeightDto>> userWeightHistory (@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.userWeightHistory(id));
    }

    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    @PutMapping("change-password")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody UserDto userDto){
        ResponseDto responseDto = userService.editPassword(userDto);
        return responseUtilService.sendResponse(responseDto);
    }
}
