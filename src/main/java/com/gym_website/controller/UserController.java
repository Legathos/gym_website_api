package com.gym_website.controller;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
import com.gym_website.dto.UserLoginDto;
import com.gym_website.dto.UserWeightDto;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserController(UserService userService, ResponseUtilService responseUtilService) {
        this.userService = userService;
        this.responseUtilService = responseUtilService;
    }



    @PostMapping("login")
        public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDto) {
        UserDto userDto = userService.loginUser(userLoginDto);
        if (userDto != null) {
            return ResponseEntity.ok("login successful");
        }
        return ResponseEntity.ok("error");
    }
    @PostMapping("register")
    public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto) {
        ResponseDto responseDto = userService.register(userDto);
        return responseUtilService.sendResponse(responseDto);
    }
    
    @GetMapping("get-info-{id}")
    public ResponseEntity<UserDto> getUserInfoById (@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.getUserInfoById(id));
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @DeleteMapping("delete-user-{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("edit-user")
    public ResponseEntity<ResponseDto> editUser(@RequestBody UserDto userDto) {
        ResponseDto responseDto = userService.editUser(userDto);
        return responseUtilService.sendResponse(responseDto);
    }

    @PostMapping("update-weight")
    public ResponseEntity<ResponseDto> updateWeight(@RequestBody UserWeightDto userWeightDto){
        ResponseDto responseDto = userService.updateUserWeight(userWeightDto);
        return responseUtilService.sendResponse(responseDto);
    }

    @GetMapping("user-{id}-weight-history")
    public ResponseEntity<List<UserWeightDto>> userWeightHistory (@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.userWeightHistory(id));
    }
    @PutMapping("change-password")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody UserDto userDto){
        ResponseDto responseDto = userService.editPassword(userDto);
        return responseUtilService.sendResponse(responseDto);
    }
}
