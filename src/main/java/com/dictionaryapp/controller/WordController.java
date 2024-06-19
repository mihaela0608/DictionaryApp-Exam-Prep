package com.dictionaryapp.controller;

import com.dictionaryapp.config.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordController {
    private final CurrentUser currentUser;

    public WordController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/add-word")
    public String addWord(){
        if (!currentUser.isUserLoggedIn()){
            return "redirect:/";
        }
        return "word-add";
    }
}
