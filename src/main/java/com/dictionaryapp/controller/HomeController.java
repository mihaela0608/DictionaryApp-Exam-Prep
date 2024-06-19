package com.dictionaryapp.controller;

import com.dictionaryapp.config.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String notLoggedIn(){
        if (currentUser.isUserLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }
    @GetMapping("/home")
    public String loggedIn(){
        if (!currentUser.isUserLoggedIn()){
            return "redirect:/";
        }
        return "home";
    }


}
