package com.example.mybatis.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public class ResponseDto {
    
    private Object data;
    private String message;

}
