package com.dictionaryapp.controller;

import com.dictionaryapp.config.CurrentUser;
import com.dictionaryapp.model.dto.UserLoginDto;
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
    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/login")
    public String viewLogin(Model model){
        if (currentUser.isUserLoggedIn()){
            return "redirect:/";
        }
        if (!model.containsAttribute("userLogin")){
            model.addAttribute("userLogin", new UserLoginDto());
        }
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLogin", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogin", bindingResult);
            return "redirect:/login";
        }
        boolean success = userService.login(userLoginDto);
        if (!success){
            redirectAttributes.addFlashAttribute("userLogin", userLoginDto);
            redirectAttributes.addFlashAttribute("userPassMismatch", true);
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String viewRegister(Model model){
        if (currentUser.isUserLoggedIn()){
            return "redirect:/";
        }
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
    @PostMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }
}
