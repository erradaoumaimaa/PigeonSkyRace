package com.pigeonskyrace.controller;

import com.pigeonskyrace.dto.request.UserRequestDTO;
import com.pigeonskyrace.dto.reponse.UserReponseDTO;
import com.pigeonskyrace.mapper.UserMapper;
import com.pigeonskyrace.model.User;
import com.pigeonskyrace.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequest) {
        try {
            User user = userMapper.toUser(userRequest);
            User createdUser = userService.registerNewUser(user);
            UserReponseDTO userResponseDTO = userMapper.toUserResponseDto(createdUser);
            return ResponseEntity.status(201).body(userResponseDTO);
        } catch (ConstraintViolationException e) {
            StringBuilder violations = new StringBuilder("Validation errors: ");
            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                violations.append(violation.getMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(violations.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'enregistrement de l'utilisateur : " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<UserReponseDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserReponseDTO> userResponseDTOs = users.stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponseDTOs);
    }


}
