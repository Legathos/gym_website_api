package com.gym_website.service.utils;

import com.gym_website.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseUtilService {
    public ResponseEntity<ResponseDto> sendResponse(ResponseDto responseDto) {
        if (responseDto.getErrorMessage() == null)
            return ResponseEntity.ok(responseDto);
        else return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }
}