package com.gym_website.service.utils;

import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
public class TimeUtilService {

        public LocalDateTime getTime (){
            return LocalDateTime.now();
        }

    }