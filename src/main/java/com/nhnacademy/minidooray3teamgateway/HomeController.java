package com.nhnacademy.minidooray3teamgateway;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(HttpSession session) {
        ModelAndView mav = new ModelAndView("home");
        String loginName = (String) session.getAttribute("loginName");

//        if (loginName == null) {
//            mav.setViewName("redirect:/auth/login");
//        } else {
            mav.addObject("loginName", loginName);
        //}
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {session.invalidate();}
        return "redirect:/";
    }
}

