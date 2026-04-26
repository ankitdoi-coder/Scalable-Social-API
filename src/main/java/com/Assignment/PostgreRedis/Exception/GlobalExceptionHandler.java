package com.Assignment.PostgreRedis.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public HashMap<String, Object> ExceptionHandler(Exception ex){
        HashMap<String,Object> map= new HashMap<>();
        map.put("timestamp",LocalDateTime.now());
        map.put("message",ex.getMessage());
        map.put("error","INTERNAL SERVER ERROR");
        

        return map;

    }
}
