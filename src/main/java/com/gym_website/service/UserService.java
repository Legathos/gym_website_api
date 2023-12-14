package com.gym_website.service;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
import com.gym_website.dto.UserLoginDto;
import com.gym_website.dto.UserWeightDto;
import com.gym_website.entity.UserEntity;
import com.gym_website.entity.UserWeightEntity;
import com.gym_website.mapper.UserMapper;
import com.gym_website.mapper.UserWeightMapper;
import com.gym_website.repository.UserWeightRepository;
import com.gym_website.service.utils.TimeUtilService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gym_website.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserWeightRepository userWeightRepository;
    private final TimeUtilService timeUtilService;
    private final UserWeightMapper userWeightMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserWeightRepository userWeightRepository, TimeUtilService timeUtilService, UserWeightMapper userWeightMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userWeightRepository = userWeightRepository;
        this.timeUtilService = timeUtilService;
        this.userWeightMapper = userWeightMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseDto register(UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        UserEntity userEntity = userMapper.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        responseDto.setSuccessMessage("User added successfully!");

        UserWeightEntity userWeightEntity = new UserWeightEntity();
        userWeightEntity.setUser_id(userEntity.getId());
        userWeightEntity.setWeight(userDto.getWeight());
        userWeightEntity.setDate(timeUtilService.getTime());

        userWeightRepository.save(userWeightEntity);

        return responseDto;
    }


    public UserDto getUserInfoById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userMapper.toDto(userEntity);
    }

    public UserDto loginUser(UserLoginDto userLoginDto) {
        UserEntity currentUser = userRepository.findByUsername(userLoginDto.getUsername());
        if (currentUser != null) {
            if (passwordEncoder.matches(userLoginDto.getPassword(), currentUser.getPassword()))
                return userMapper.toDto(currentUser);
        }
        return null;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getAllUsers(){
        return userMapper.toDtos(userRepository.findAll());
    }


    public ResponseDto editUser(UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        Optional<UserEntity> toEditOptional = userRepository.findById(userDto.getId());
        if (toEditOptional.isEmpty()) {
            responseDto.setErrorMessage("User not found!");
            return responseDto;
        }
        UserEntity toEdit = toEditOptional.get();

        toEdit.setAge(userDto.getAge());
        toEdit.setRole(userDto.getRole());
        toEdit.setGender(userDto.getGender());
        toEdit.setHeight(userDto.getHeight());
        toEdit.setWeight(userDto.getWeight());
        toEdit.setPassword(passwordEncoder.encode(userDto.getPassword()));
        toEdit.setUsername(userDto.getUsername());

        userRepository.save(toEdit);

        UserWeightEntity userWeightEntity = new UserWeightEntity();

        userWeightEntity.setUser_id(userDto.getId());
        userWeightEntity.setWeight(userDto.getWeight());
        userWeightEntity.setDate(timeUtilService.getTime());

        userWeightRepository.save(userWeightEntity);



        responseDto.setSuccessMessage("User updated successfully!");
        return responseDto;
    }

    public ResponseDto updateUserWeight (UserWeightDto userWeightDto){
        UserWeightEntity userWeightEntity = new UserWeightEntity();
        userWeightEntity.setUser_id(userWeightDto.getUser_id());
        userWeightEntity.setWeight(userWeightDto.getWeight());
        userWeightEntity.setDate(timeUtilService.getTime());
        userWeightRepository.save(userWeightEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("User weight updated");
        return responseDto;
    }

    public List<UserWeightDto> userWeightHistory (Long id){
        List<UserWeightDto> userWeightDtos = userWeightMapper.toDtos((List<UserWeightEntity>) userWeightRepository.findByUserId(id));
        return userWeightDtos;
    }

    public ResponseDto editPassword(UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        UserEntity userEntity = userRepository.findById(userDto.getId()).orElse(null);
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userEntity);
        responseDto.setSuccessMessage("User password changed successfully!");
        return responseDto;
    }

}
