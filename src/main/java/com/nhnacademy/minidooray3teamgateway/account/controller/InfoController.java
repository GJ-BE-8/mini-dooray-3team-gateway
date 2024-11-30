package com.nhnacademy.minidooray3teamgateway.account.controller;

import com.nhnacademy.minidooray3teamgateway.account.dto.AccountInfoDto;
import com.nhnacademy.minidooray3teamgateway.account.feign.RegisterServiceClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {

    private final RegisterServiceClient registerServiceClient;

    public InfoController(RegisterServiceClient registerServiceClient) {
        this.registerServiceClient = registerServiceClient;
    }

    @GetMapping("/accounts/info/{id}")
    public ModelAndView AccountInfo(@PathVariable("id") Long id, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        if (loginName == null || loginName.isEmpty()) {
            return new ModelAndView("redirect:/login");
        }

        ResponseEntity<AccountInfoDto> accountInfoDtoResponseEntity = registerServiceClient.showAccountInfo(id);


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("accountInfoDto", accountInfoDtoResponseEntity.getBody());
        modelAndView.setViewName("account-info");
        return modelAndView;
    }
}
