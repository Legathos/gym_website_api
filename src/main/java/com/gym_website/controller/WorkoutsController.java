package com.gym_website.controller;

import com.gym_website.dto.WorkoutsDto;
import com.gym_website.service.WorkoutsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("workouts")
public class WorkoutsController {

    private final WorkoutsService workoutsService;

    public WorkoutsController(WorkoutsService workoutsService) {
        this.workoutsService = workoutsService;
    }


    @PostMapping("add-workout")
    public ResponseEntity addWorkout(@RequestBody WorkoutsDto workoutsDto){
        return ResponseEntity.ok(workoutsService.addWorkout(workoutsDto));
    }

    @GetMapping("get-workout-{id}")
    public ResponseEntity getWorkoutById(@PathVariable("id")Long id){
        return ResponseEntity.ok(workoutsService.getWorkout(id));
    }

    @GetMapping("get-all-workouts")
    public ResponseEntity getAllWorkouts(){
        return ResponseEntity.ok(workoutsService.getAllWorkouts());
    }

    @PutMapping("edit-workout")
    public ResponseEntity editWorkout(@RequestBody WorkoutsDto workoutsDto){
        return ResponseEntity.ok(workoutsService.editWorkout(workoutsDto));
    }

    @DeleteMapping("delete-workout-{id}")
    public ResponseEntity deleteWorkout(@PathVariable("id")Long id){
        return ResponseEntity.ok(workoutsService.deleteWorkout(id));
    }


}
