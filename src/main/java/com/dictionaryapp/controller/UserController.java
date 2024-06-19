package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.UserRegisterDto;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(){
        return "redirect:/";
    }

    @GetMapping("/register")
    public String viewRegister(Model model){
        if (!model.containsAttribute("userData")){
            model.addAttribute("userData", new UserRegisterDto());
        }
        return "register";
    }
    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDto userRegisterDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !userService.register(userRegisterDto)){
            redirectAttributes.addFlashAttribute("userData", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userData", bindingResult);
            return "redirect:/register";
        }

        return "redirect:/";
    }
}
