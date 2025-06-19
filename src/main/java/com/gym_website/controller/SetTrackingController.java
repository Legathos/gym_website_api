package com.gym_website.controller;

import com.gym_website.dto.SetTrackingDto;
import com.gym_website.service.SetTrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/set-tracking")
public class SetTrackingController {

    private final SetTrackingService setTrackingService;

    public SetTrackingController(SetTrackingService setTrackingService) {
        this.setTrackingService = setTrackingService;
    }

    @PostMapping("/add-set")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity addSetTracking(@RequestBody SetTrackingDto setTrackingDto) {
        return ResponseEntity.ok(setTrackingService.addSetTracking(setTrackingDto));
    }

    @GetMapping("/get-set/{id}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getSetTracking(@PathVariable("id") Long id) {
        return ResponseEntity.ok(setTrackingService.getSetTracking(id));
    }

    @GetMapping("/get-all-sets")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getAllSetTrackings() {
        return ResponseEntity.ok(setTrackingService.getAllSetTrackings());
    }

    @GetMapping("/get-sets-by-workout/{workoutId}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getSetTrackingsByWorkoutId(@PathVariable("workoutId") Long workoutId) {
        return ResponseEntity.ok(setTrackingService.getSetTrackingsByWorkoutId(workoutId));
    }

    @GetMapping("/get-sets-by-exercise/{exerciseId}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getSetTrackingsByExerciseId(@PathVariable("exerciseId") Long exerciseId) {
        return ResponseEntity.ok(setTrackingService.getSetTrackingsByExerciseId(exerciseId));
    }

    @GetMapping("/get-set-by-workout-and-exercise/{workoutId}/{exerciseId}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getSetTrackingsByWorkoutIdAndExerciseId(
            @PathVariable("workoutId") Long workoutId,
            @PathVariable("exerciseId") Long exerciseId) {
        return ResponseEntity.ok(setTrackingService.getSetTrackingsByWorkoutIdAndExerciseId(workoutId, exerciseId));
    }

    @PutMapping("/update-set")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity updateSetTracking(@RequestBody SetTrackingDto setTrackingDto) {
        return ResponseEntity.ok(setTrackingService.editSetTracking(setTrackingDto));
    }

    @DeleteMapping("/delete-set/{id}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity deleteSetTracking(@PathVariable("id") Long id) {
        return ResponseEntity.ok(setTrackingService.deleteSetTracking(id));
    }
}
