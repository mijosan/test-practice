package com.example.mybatis.common;

import com.example.mybatis.dto.ResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtil {
    
    public static ResponseEntity<ResponseDto> getResponseEntity(Object data, HttpStatus httpStatus, String message) {
        ResponseEntity<ResponseDto> responseEntity = new ResponseEntity<ResponseDto>(ResponseDto.builder()
                                                                                                    .data(data)
                                                                                                    .message(message)
                                                                                                    .build()
                                                                                                    , httpStatus);
        
        return responseEntity;
    }

}
