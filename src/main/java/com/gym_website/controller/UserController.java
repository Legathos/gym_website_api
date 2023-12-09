package com.gym_website.controller;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
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
}
