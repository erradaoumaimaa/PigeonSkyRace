package com.pigeonskyrace.controller;
import com.pigeonskyrace.dto.reponse.UserReponseDTO;
import com.pigeonskyrace.dto.request.UserRequestDTO;
import com.pigeonskyrace.model.User;
import com.pigeonskyrace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserReponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequest) {
        User user = userService.registerUser(userRequest);
        UserReponseDTO userResponse = new UserReponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
