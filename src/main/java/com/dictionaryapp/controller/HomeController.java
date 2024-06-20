package com.dictionaryapp.controller;

import com.dictionaryapp.config.CurrentUser;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;


    public HomeController(CurrentUser currentUser, WordService wordService, WordRepository wordRepository, LanguageRepository languageRepository) {
        this.currentUser = currentUser;
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
    }

    @GetMapping("/")
    public String notLoggedIn(){
        if (currentUser.isUserLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }
    @GetMapping("/home")
    public String loggedIn(Model model){
        if (!currentUser.isUserLoggedIn()){
            return "redirect:/";
        }
        model.addAttribute("german", wordRepository.findAllByLanguage(languageRepository.findByLanguageName(LanguageEnum.GERMAN).get()));
        model.addAttribute("french", wordRepository.findAllByLanguage(languageRepository.findByLanguageName(LanguageEnum.FRENCH).get()));
        model.addAttribute("spanish", wordRepository.findAllByLanguage(languageRepository.findByLanguageName(LanguageEnum.SPANISH).get()));
        model.addAttribute("italian", wordRepository.findAllByLanguage(languageRepository.findByLanguageName(LanguageEnum.ITALIAN).get()));
        return "home";
    }


}
