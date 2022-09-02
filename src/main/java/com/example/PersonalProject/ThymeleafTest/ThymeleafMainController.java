package com.example.PersonalProject.ThymeleafTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafMainController {

    @GetMapping("/thymeleaf/login")
    public String login() {

        return "login";
    }
}
