package com.gym_website.controller;

import com.gym_website.dto.FoodDto;
import com.gym_website.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("get-food-items-{name}")
    @PreAuthorize("hasAnyAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getFoodItemsByName(@PathVariable("name")String name){
        return ResponseEntity.ok(this.foodService.getFoodItemsByName(name));
    }

    @GetMapping("/get-food-item-barcode-{barcode}")
    @PreAuthorize("hasAnyAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity<?> getFoodData(@PathVariable String barcode) {
        String apiUrl = "https://world.openfoodfacts.org/api/v0/product/" + barcode + ".json";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch food data.");
        }
    }

    @GetMapping("/get-food-item-by-barcode-{barcode}")
    @PreAuthorize("hasAnyAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getFoodItemByBarcode(@PathVariable Long barcode) {
        return ResponseEntity.ok(this.foodService.getFoodItemByBarcode(barcode));
    }
}
