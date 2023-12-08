package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ExercisesService;

@Controller
@RequestMapping("/exercises")
public class ExercisesController {

    private final ExercisesService exercisesService;
    public ExercisesController(ExercisesService exercisesService){
        this.exercisesService = exercisesService;
    }




}
