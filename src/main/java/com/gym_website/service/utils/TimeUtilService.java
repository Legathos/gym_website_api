package com.gym_website.service.utils;

import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TimeUtilService {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        public LocalDateTime getTime (){
            return LocalDateTime.now();
        }

    }