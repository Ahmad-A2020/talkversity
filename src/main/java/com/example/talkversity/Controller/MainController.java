package com.example.talkversity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage(){
        return "home.html";
    }

    @GetMapping("/login")
    public String logIn(){
        return "login.html";
    }



}
