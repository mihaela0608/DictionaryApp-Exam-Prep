package com.dictionaryapp.controller;

import com.dictionaryapp.config.CurrentUser;
import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {
    private final CurrentUser currentUser;
    private final WordService wordService;

    public WordController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping("/add-word")
    public String addWordView(Model model){
        if (!currentUser.isUserLoggedIn()){
            return "redirect:/";
        }
        if (!model.containsAttribute("wordData")){
            model.addAttribute("wordData", new AddWordDto());
        }
        return "word-add";
    }
    @PostMapping("/add-word")
    public String addWord(@Valid AddWordDto addWordDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("wordData", addWordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordData", bindingResult);
            return "redirect:/add-word";
        }
        wordService.addWord(addWordDto);
        return "redirect:/home";
    }
}
