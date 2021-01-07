package com.example.mybatis.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseDto {
    
    private Object data;
    private String message;

}
