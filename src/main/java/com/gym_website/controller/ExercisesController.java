package com.gym_website.controller;

import com.gym_website.dto.ExercisesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gym_website.service.ExercisesService;

@Controller
@RequestMapping("/exercises")
public class ExercisesController {

    private final ExercisesService exercisesService;
    public ExercisesController(ExercisesService exercisesService){
        this.exercisesService = exercisesService;
    }

    @PostMapping("add-exercise")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity addExercise (@RequestBody ExercisesDto exercisesDto){
        return ResponseEntity.ok(exercisesService.addExercise(exercisesDto));
    }

    @GetMapping("get-exercise-{id}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getExercise(@PathVariable("id")Long id){
        return ResponseEntity.ok(exercisesService.getExercise(id));
    }

    @GetMapping("get-all-exercises")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getAllExercises(){
        return ResponseEntity.ok(exercisesService.gelAllExercises());
    }


    @GetMapping("get-{category}-exercises")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getExercisesByCategory(@PathVariable("category")Long category){
        return ResponseEntity.ok(exercisesService.gelExercisesByCategory(String.valueOf(category)));
    }

    @DeleteMapping("delete-exercise-{id}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity deleteById(@PathVariable("id")Long id){
        return ResponseEntity.ok(exercisesService.deleteExercise(id));
    }

    @PutMapping("edit-exercise")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity editExercise(@RequestBody ExercisesDto exercisesDto){
        return ResponseEntity.ok(exercisesService.editExercise(exercisesDto));
    }

}
