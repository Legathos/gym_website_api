package com.gym_website.service;

import com.gym_website.dto.FoodDto;
import com.gym_website.dto.ResponseDto;
import com.gym_website.entity.FoodEntity;
import com.gym_website.mapper.FoodMapper;
import com.gym_website.repository.FoodRepository;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }


    public ResponseDto addFoodItem(FoodDto foodDto){
        FoodEntity foodEntity = foodMapper.toEntity(foodDto);
        foodRepository.save(foodEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Food item added");
        return responseDto;
    }

    public ResponseDto editFoodItem(FoodDto foodDto){
        FoodEntity foodEntity = foodRepository.findById(foodDto.getId()).orElse(null);
        foodEntity.setName(foodDto.getName());
        foodEntity.setCalories(foodDto.getCalories());
        foodEntity.setCarbs(foodDto.getCarbs());
        foodEntity.setProtein(foodDto.getProtein());
        foodEntity.setFats(foodDto.getFats());
        foodEntity.setWeight(foodDto.getWeight());
        foodRepository.save(foodEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Food item modified");
        return responseDto;
    }

    public ResponseDto deleteFoodItem(Long id){
        ResponseDto responseDto =new ResponseDto();
        if (foodRepository.findById(id).orElse(null)!= null) {
            foodRepository.delete(foodRepository.findById(id).orElse(null));
            responseDto.setSuccessMessage("Food item deleted");
            return responseDto;
        }
        responseDto.setErrorMessage("Item not found");
        return responseDto;
    }

    public FoodDto getFoodItem(Long id){
        FoodEntity foodEntity = foodRepository.findById(id).orElse(null);
        return foodMapper.toDto(foodEntity);
    }

    public List<FoodDto> getFoodItems(){
        List<FoodEntity> foodEntities = foodRepository.findAll();
        return foodMapper.toDtos(foodEntities);
    }

}
