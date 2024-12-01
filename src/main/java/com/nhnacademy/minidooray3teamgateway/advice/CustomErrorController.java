package com.nhnacademy.minidooray3teamgateway.advice;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "알 수 없는 에러가 발생했습니다.";

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == 404) {
                errorMessage = "페이지를 찾을 수 없습니다.";
            } else if (statusCode == 500) {
                errorMessage = "서버 에러가 발생했습니다.";
            }
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error-page";
    }
}
