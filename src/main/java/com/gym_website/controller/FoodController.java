package com.gym_website.controller;

import com.gym_website.dto.FoodDto;
import com.gym_website.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("add-food-item")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity addFoodItem(@RequestBody FoodDto foodDto){
        return ResponseEntity.ok(this.foodService.addFoodItem(foodDto));
    }

    @PutMapping("edit-food-item")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity editFoodItem(@RequestBody FoodDto foodDto){
        return ResponseEntity.ok(this.foodService.editFoodItem(foodDto));
    }

    @DeleteMapping("delete-food-item-{id}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity deleteFoodItem(@PathVariable("id")Long id){
        return ResponseEntity.ok(this.foodService.deleteFoodItem(id));
    }

    @GetMapping("get-food-item-{id}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getFoodItem(@PathVariable("id")Long id){
        return ResponseEntity.ok(this.foodService.getFoodItem(id));
    }

    @GetMapping("get-food-items")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getFoodItems(){
        return ResponseEntity.ok(this.foodService.getFoodItems());
    }
}
