package com.gym_website.controller;

import com.gym_website.dto.LoggerDto;
import com.gym_website.service.LoggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/logger")
public class LoggerController {

    private final LoggerService loggerService;

    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @PostMapping("add-new-log")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity addNewLog(@RequestBody LoggerDto loggerDto){
        return ResponseEntity.ok(this.loggerService.addNewLog(loggerDto));
    }

    @GetMapping("get-logs-for-{user}-{date}")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity getLogsByUserIdAndDate(@PathVariable("user")Long userId, @PathVariable("date") Date date){
        return ResponseEntity.ok(this.loggerService.getLogsForUserIdAndDate(userId,date));
    }

    @PutMapping("edit-log")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity editLog(@RequestBody LoggerDto loggerDto){
        return ResponseEntity.ok(this.loggerService.editLog(loggerDto));
    }

    @DeleteMapping("delete-log")
    @PreAuthorize("hasAuthority(T(com.gym_website.util.enums.EAppRoles).MEMBER)")
    public ResponseEntity deleteLog(@RequestBody LoggerDto loggerDto){
        return ResponseEntity.ok(this.loggerService.deleteLog(loggerDto));
    }
}
