package com.nhnacademy.minidooray3teamgateway.account.controller;

import com.nhnacademy.minidooray3teamgateway.account.feign.RegisterServiceClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountModifyController {

    private final RegisterServiceClient registerServiceClient;

    public AccountModifyController(RegisterServiceClient registerServiceClient) {
        this.registerServiceClient = registerServiceClient;
    }

    @GetMapping("/accounts/{id}")
    public ModelAndView editAccount(@PathVariable("id") Long id, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");

        if (loginName == null || loginName.isEmpty()) {
            return new ModelAndView("redirect:/login");
        }

        Long loggedInUserId = registerServiceClient.getId(loginName);

        if (!loggedInUserId.equals(id)) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("edit-account");
        mav.addObject("id", id); // 수정 페이지에서 사용할 사용자 ID
        return mav;
    }





}
