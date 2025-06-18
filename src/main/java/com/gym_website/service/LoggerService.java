package com.gym_website.service;

import com.gym_website.dto.LoggerDto;
import com.gym_website.dto.ResponseDto;
import com.gym_website.entity.LoggerEntity;
import com.gym_website.mapper.LoggerMapper;
import com.gym_website.repository.LoggerRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LoggerService {

    private final LoggerRepository loggerRepository;
    private  final LoggerMapper loggerMapper;

    public LoggerService(LoggerRepository loggerRepository, LoggerMapper loggerMapper) {
        this.loggerRepository = loggerRepository;
        this.loggerMapper = loggerMapper;
    }

    public ResponseDto addNewLog(LoggerDto loggerDto){
        LoggerEntity loggerEntity  = loggerMapper.toEntity(loggerDto);
        loggerRepository.save(loggerEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Log added successfully");
        return responseDto;
    }

    public List<LoggerDto> getLogsForUserIdAndDate(Long userId, String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        return loggerMapper.toDtos(loggerRepository.findByUserIdAndDate(userId,date));
    }

    public ResponseDto editLog(LoggerDto loggerDto) {
    LoggerEntity loggerEntity = loggerRepository.findById(loggerDto.getId()).orElse(null);
    assert loggerEntity != null;
    loggerEntity.setCalories(loggerDto.getCalories());
    loggerEntity.setDate(loggerDto.getDate());
    loggerEntity.setFats(loggerDto.getFats());
    loggerEntity.setCarbs(loggerDto.getCarbs());
    loggerEntity.setProtein(loggerDto.getProtein());
    loggerEntity.setWeight(loggerDto.getWeight());
    loggerEntity.setFood_name(loggerDto.getFood_name());

    loggerRepository.save(loggerEntity);

    ResponseDto responseDto = new ResponseDto();
    responseDto.setSuccessMessage("Log edited successfully");
    return responseDto;
    }

    public ResponseDto deleteLog(LoggerDto loggerDto){
        loggerRepository.delete(Objects.requireNonNull(loggerRepository.findById(loggerDto.getId()).orElse(null)));
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Log deleted");
        return responseDto;
    }

    public LoggerDto sumMacrosForUserIdAndDate(Long userId, String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        Map<String, Object> macroSums = loggerRepository.sumMacrosByUserIdAndDate(userId, date);

        LoggerDto sumDto = new LoggerDto();
        sumDto.setUser_id(userId);
        sumDto.setDate(date);

        // Handle potential null values from the query
        sumDto.setCalories(macroSums.get("calories") != null ? ((Number) macroSums.get("calories")).longValue() : 0L);
        sumDto.setCarbs(macroSums.get("carbs") != null ? ((Number) macroSums.get("carbs")).doubleValue() : 0.0);
        sumDto.setFats(macroSums.get("fats") != null ? ((Number) macroSums.get("fats")).doubleValue() : 0.0);
        sumDto.setProtein(macroSums.get("protein") != null ? ((Number) macroSums.get("protein")).doubleValue() : 0.0);

        return sumDto;
    }

    /**
     * Get daily macro summaries for a user within a date range from today to X days ago
     * @param userId The user ID
     * @param days Number of days to look back from today
     * @return List of daily macro summary DTOs within the date range
     */
    public List<LoggerDto> getLogsForUserIdAndDateRange(Long userId, int days) {
        // Get current date as end date
        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();

        // Calculate start date by subtracting days from current date
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        Date startDate = calendar.getTime();

        // Create a list to store daily summaries
        List<LoggerDto> dailySummaries = new ArrayList<>();

        // Create a SimpleDateFormat for converting dates to strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Clone the calendar with start date
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(startDate);

        // Iterate through each day in the range
        while (!currentCal.getTime().after(endDate)) {
            try {
                // Get the date string for the current day
                String dateString = dateFormat.format(currentCal.getTime());

                // Get the summary for this day
                LoggerDto daySummary = sumMacrosForUserIdAndDate(userId, dateString);

                // Add to our list of summaries
                dailySummaries.add(daySummary);

                // Move to the next day
                currentCal.add(Calendar.DAY_OF_MONTH, 1);
            } catch (ParseException e) {
                // Skip this day if there's a parsing error
                currentCal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        return dailySummaries;
    }
}
