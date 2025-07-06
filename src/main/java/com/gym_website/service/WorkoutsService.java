package com.gym_website.service;

import com.gym_website.dto.MonthlyWorkoutsDto;
import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.WorkoutsDto;
import com.gym_website.entity.WorkoutsEntity;
import com.gym_website.mapper.WorkoutsMapper;
import com.gym_website.repository.WorkoutsRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Calendar;

@Service
public class WorkoutsService {

    private final WorkoutsMapper workoutsMapper;
    private final WorkoutsRepository workoutsRepository;

    public WorkoutsService(WorkoutsMapper workoutsMapper, WorkoutsRepository workoutsRepository) {
        this.workoutsMapper = workoutsMapper;
        this.workoutsRepository = workoutsRepository;
    }

    public ResponseDto addWorkout(WorkoutsDto workoutsDto){
        ResponseDto responseDto = new ResponseDto();
        WorkoutsEntity workoutsEntity = workoutsMapper.toEntity(workoutsDto);
        workoutsEntity = workoutsRepository.save(workoutsEntity);
        responseDto.setSuccessMessage("Workout added");
        responseDto.setId(workoutsEntity.getId());
        return responseDto;
    }

    public WorkoutsDto getWorkout(Long id){
        WorkoutsEntity workoutsEntity = workoutsRepository.findById(id).orElse(null);
        return workoutsMapper.toDto(workoutsEntity);
    }

    public List<WorkoutsDto> getAllWorkouts(){
        List<WorkoutsEntity> workoutsEntities = workoutsRepository.findAll();
        return workoutsMapper.toDtos(workoutsEntities);
    }

    public List<WorkoutsDto> getAllWorkoutsByUserId(Long id){
        List<WorkoutsEntity> workoutsEntities = workoutsRepository.findByUserId(id);
        return  workoutsMapper.toDtos(workoutsEntities);
    }

    public WorkoutsDto getLatestWorkoutByUserId(Long userId) {
        List<WorkoutsEntity> workoutsEntities = workoutsRepository.findByUserIdOrderByDateDesc(userId);
        if (workoutsEntities.isEmpty()) {
            return null;
        }
        return workoutsMapper.toDto(workoutsEntities.get(0));
    }

    public ResponseDto editWorkout (WorkoutsDto workoutsDto){
        ResponseDto responseDto = new ResponseDto();
        WorkoutsEntity workoutsEntity = new WorkoutsEntity();
        workoutsEntity.setId(workoutsDto.getId());
        workoutsEntity.setDate(workoutsDto.getDate());
        workoutsEntity.setUser_id(workoutsDto.getUser_id());
        workoutsEntity.setName(workoutsDto.getName());
        workoutsEntity.setTotal_weight(workoutsDto.getTotal_weight());
        workoutsRepository.save(workoutsEntity);
        responseDto.setSuccessMessage("Workout edited");
        return responseDto;
    }

    public ResponseDto deleteWorkout (Long id){
        ResponseDto responseDto =new ResponseDto();
        if(workoutsRepository.findById(id).isPresent()) {
            workoutsRepository.deleteById(id);
            responseDto.setSuccessMessage("Workout deleted");
            return responseDto;
        }
        responseDto.setErrorMessage("Workout not found");
        return responseDto;
    }

    public MonthlyWorkoutsDto getWorkoutsByMonth(Long userId, int year, int month) {
        // Create calendar for start of month
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(year, month - 1, 1, 0, 0, 0); // Month is 0-based in Calendar
        startCalendar.set(Calendar.MILLISECOND, 0);
        Date startDate = startCalendar.getTime();

        // Create calendar for end of month (start of next month)
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(year, month, 1, 0, 0, 0); // Next month
        endCalendar.set(Calendar.MILLISECOND, 0);
        Date endDate = endCalendar.getTime();

        // Get workouts for the month
        List<WorkoutsEntity> workoutsEntities = workoutsRepository.findByUserIdAndMonth(userId, startDate, endDate);
        List<WorkoutsDto> workoutsDtos = workoutsMapper.toDtos(workoutsEntities);

        // Create map of day of month to workout
        Map<Integer, WorkoutsDto> dailyWorkouts = new HashMap<>();

        // Initialize all days of the month with null
        int daysInMonth = startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int day = 1; day <= daysInMonth; day++) {
            dailyWorkouts.put(day, null);
        }

        // Fill in workouts where they exist
        for (WorkoutsDto workout : workoutsDtos) {
            Calendar workoutCalendar = Calendar.getInstance();
            workoutCalendar.setTime(workout.getDate());
            int dayOfMonth = workoutCalendar.get(Calendar.DAY_OF_MONTH);
            dailyWorkouts.put(dayOfMonth, workout);
        }

        // Create and return the response DTO
        MonthlyWorkoutsDto monthlyWorkoutsDto = new MonthlyWorkoutsDto();
        monthlyWorkoutsDto.setUserId(userId);
        monthlyWorkoutsDto.setYear(year);
        monthlyWorkoutsDto.setMonth(month);
        monthlyWorkoutsDto.setDailyWorkouts(dailyWorkouts);

        return monthlyWorkoutsDto;
    }











}
