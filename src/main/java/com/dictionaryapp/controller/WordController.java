package com.dictionaryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordController {
    @GetMapping("/add-word")
    public String addWord(){
        return "word-add";
    }
}
