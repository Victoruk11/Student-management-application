package com.victoruk.student_management.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showLoginPage() {
        return "login"; // This corresponds to login.html in the templates folder
    }
}
