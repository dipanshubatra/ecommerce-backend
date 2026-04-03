package com.dipanshu.user_service.service;

import com.dipanshu.user_service.exception.UserAlreadyExistException;
import com.dipanshu.user_service.entity.User;
import com.dipanshu.user_service.exception.UserNotFoundException;
import com.dipanshu.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistException("Email already exists");
        }

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}