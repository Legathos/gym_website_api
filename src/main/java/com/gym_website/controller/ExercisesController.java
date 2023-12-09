package com.gym_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gym_website.service.ExercisesService;

@Controller
@RequestMapping("/exercises")
public class ExercisesController {

    private final ExercisesService exercisesService;
    public ExercisesController(ExercisesService exercisesService){
        this.exercisesService = exercisesService;
    }




}
