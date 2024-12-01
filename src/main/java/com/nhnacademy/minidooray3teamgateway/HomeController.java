package com.nhnacademy.minidooray3teamgateway;

import com.nhnacademy.minidooray3teamgateway.account.feign.RegisterServiceClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final RegisterServiceClient registerServiceClient;

    public HomeController(RegisterServiceClient registerServiceClient) {
        this.registerServiceClient = registerServiceClient;
    }

    @GetMapping("/")
    public ModelAndView home(HttpSession session) {
        ModelAndView mav = new ModelAndView("home");

        String loginName = (String) session.getAttribute("loginName");

        if (loginName == null) {
            mav.setViewName("redirect:/auth/login");
            return mav;
        }

        Long id = registerServiceClient.getId(loginName);
        mav.addObject("loginName", loginName);
        session.setAttribute("id", id);

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

    @GetMapping("/projects/{projectId}/milestones")
    ModelAndView milestones(@PathVariable Long projectId) {
        ModelAndView modelAndView = new ModelAndView("create-milestone");
        modelAndView.addObject("projectId", projectId);
        return modelAndView;
    }
}

