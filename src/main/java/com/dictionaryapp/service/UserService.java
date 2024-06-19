package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginDto;
import com.dictionaryapp.model.dto.UserRegisterDto;

public interface UserService {
    boolean register(UserRegisterDto userRegisterDto);
    boolean login(UserLoginDto userLoginDto);
    void logout();
}
