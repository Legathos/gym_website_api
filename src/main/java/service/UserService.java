package service;

import dto.ResponseDto;
import dto.UserDto;
import entity.UserEntity;
import mapper.UserMapper;import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public ResponseDto register(UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        UserEntity userEntity = userMapper.toEntity(userDto);
        userRepository.save(userEntity);
        responseDto.setSuccessMessage("User added successfully!");
        return responseDto;
    }
}
