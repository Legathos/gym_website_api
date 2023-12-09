package controller;

import dto.ResponseDto;
import dto.UserDto;
import mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;
import service.UserService;
import service.utils.ResponseUtilService;

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

}
