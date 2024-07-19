package com.eazybytes.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {
    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("role", auth.getAuthorities().toString());
//        throw new RuntimeException("Its a bad day!!");
        return "dashboard.html";
    }
}
