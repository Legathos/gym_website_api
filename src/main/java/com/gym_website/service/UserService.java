package com.gym_website.service;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
import com.gym_website.entity.UserEntity;
import com.gym_website.mapper.UserMapper;import org.springframework.stereotype.Service;
import com.gym_website.repository.UserRepository;

import java.util.List;
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
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userMapper.toDto(userEntity);
    }

    public void deleteUserById(Long id) {
        UserEntity toDelete = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
    }

    public List<UserDto> getAllUsers(){
        return userMapper.toDtos(userRepository.findAll());
    }

    public ResponseDto editUser(UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        Optional<UserEntity> toEditOptional = userRepository.findById(userDto.getId());
        if (!toEditOptional.isPresent()) {
            responseDto.setErrorMessage("User not found!");
            return responseDto;
        }
        UserEntity toEdit = toEditOptional.get();

        toEdit.setAge(userDto.getAge());
        toEdit.setRole(userDto.getRole());
        toEdit.setGender(userDto.getGender());
        toEdit.setHeight(userDto.getHeight());
        toEdit.setWeight(userDto.getWeight());
        toEdit.setPassword(userDto.getPassword());
        toEdit.setUsername(userDto.getUsername());

        userRepository.save(toEdit);
        responseDto.setSuccessMessage("User updated successfully!");
        return responseDto;
    }
}
