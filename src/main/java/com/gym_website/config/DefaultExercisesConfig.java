package com.gym_website.config;

import com.gym_website.dto.ExercisesDto;
import com.gym_website.dto.UserDto;
import com.gym_website.service.ExercisesService;
import com.gym_website.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultExercisesConfig {

    private final UserService userService;

    public DefaultExercisesConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner initDefaultExercises(ExercisesService exercisesService) {
        return args -> {
            // Create or get system user
            Long systemUserId = getOrCreateSystemUser();

            // Check if default exercises already exist to avoid duplicates
            List<ExercisesDto> allExercises = exercisesService.gelAllExercises();
            boolean defaultExercisesExist = allExercises.stream()
                    .anyMatch(exercise -> exercise.getUser_id() != null && exercise.getUser_id().equals(systemUserId));

            if (!defaultExercisesExist) {
                // Create default exercises
                List<ExercisesDto> defaultExercises = createDefaultExercises(systemUserId);

                // Save each default exercise
                for (ExercisesDto exercise : defaultExercises) {
                    exercisesService.addExercise(exercise);
                }

                System.out.println("Default exercises have been added successfully.");
            } else {
                System.out.println("Default exercises already exist. Skipping initialization.");
            }
        };
    }

    private Long getOrCreateSystemUser() {
        // Check if system user exists
        UserDto systemUser = userService.getUserInfoByUsername("system");

        if (systemUser == null) {
            // Create system user
            UserDto newSystemUser = new UserDto();
            newSystemUser.setUsername("system");
            newSystemUser.setPassword("system123"); // Use a secure password in production
            newSystemUser.setRole("SYSTEM");
            newSystemUser.setAge(0L);
            newSystemUser.setWeight(0L);
            newSystemUser.setHeight(0L);
            newSystemUser.setGender("N/A");
            newSystemUser.setEmail("system@example.com");

            userService.register(newSystemUser);

            // Get the created user to get its ID
            systemUser = userService.getUserInfoByUsername("system");
        }

        return systemUser.getId();
    }

    private List<ExercisesDto> createDefaultExercises(Long systemUserId) {
        List<ExercisesDto> exercises = new ArrayList<>();

        // Chest exercises
        addExercise(exercises, systemUserId, "Bench Press", "Chest", "A compound exercise that targets the chest, shoulders, and triceps.");
        addExercise(exercises, systemUserId, "Chest Fly", "Chest", "An isolation exercise that targets the chest muscles.");

        // Arms exercises
        addExercise(exercises, systemUserId, "Triceps Extensions", "Arms", "An isolation exercise that targets the triceps.");
        addExercise(exercises, systemUserId, "Bicep Curls", "Arms", "An isolation exercise that targets the biceps.");

        // Legs exercises
        addExercise(exercises, systemUserId, "Squats", "Legs", "A compound exercise that targets the quadriceps, hamstrings, and glutes.");
        addExercise(exercises, systemUserId, "Calf Raises", "Legs", "An isolation exercise that targets the calf muscles.");
        addExercise(exercises, systemUserId, "Leg Extensions", "Legs", "An isolation exercise that targets the quadriceps.");
        addExercise(exercises, systemUserId, "Leg Curls", "Legs", "An isolation exercise that targets the hamstrings.");

        // Shoulders exercises
        addExercise(exercises, systemUserId, "Shoulder Press", "Shoulders", "A compound exercise that targets the deltoids and triceps.");
        addExercise(exercises, systemUserId, "Lateral Raises", "Shoulders", "An isolation exercise that targets the lateral deltoids.");

        // Core exercises
        addExercise(exercises, systemUserId, "Ab Crunch", "Core", "An isolation exercise that targets the abdominal muscles.");

        return exercises;
    }

    private void addExercise(List<ExercisesDto> exercises, Long userId, String name, String category, String description) {
        ExercisesDto exercise = new ExercisesDto();
        exercise.setUser_id(userId); // Use the system user ID
        exercise.setName(name);
        exercise.setCategory(category);
        exercise.setDescription(description);
        exercises.add(exercise);
    }
}
