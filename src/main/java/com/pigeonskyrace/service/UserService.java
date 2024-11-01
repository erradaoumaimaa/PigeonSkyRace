package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.request.UserRequestDTO;
import com.pigeonskyrace.model.User;
import com.pigeonskyrace.model.enums.Role;
import com.pigeonskyrace.repository.UserRepository;
import com.pigeonskyrace.mapper.UserMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(UserRequestDTO userRequest) {
        User user = userMapper.toUser(userRequest);
        user.setId(ObjectId.get());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.ELEVEUR);

        return userRepository.save(user);
    }
}

