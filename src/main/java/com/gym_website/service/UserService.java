package com.gym_website.service;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
import com.gym_website.entity.UserEntity;
import com.gym_website.mapper.UserMapper;import org.springframework.stereotype.Service;
import com.gym_website.repository.UserRepository;

import java.util.Optional;

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

    public UserDto getUserInfoById(Long id){
        ResponseDto responseDto = new ResponseDto();
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userMapper.toDto(userEntity);
    }

    public void deleteUserById(Long id) {
        UserEntity toDelete = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
    }
}
