package com.nhnacademy.minidooray3teamgateway.advice;

import feign.FeignException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Gateway에서 요청을 처리할 수 없습니다.");
        return "error-page";
    }

    @ExceptionHandler(FeignException.class)
    public String handleFeignException(FeignException ex, Model model) {
        model.addAttribute("errorMessage", "서버와의 통신 중 문제가 발생했습니다.");
        return "error-page";
    }

//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception ex, Model model) {
//        model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
//        return "error-page";
//    }
}

