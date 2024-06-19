package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserRegisterDto;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent() || userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()){
            return false;
        }
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            return false;
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userRepository.saveAndFlush(user);
        return true;
    }
}
