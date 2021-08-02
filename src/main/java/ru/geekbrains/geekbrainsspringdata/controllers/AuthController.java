package ru.geekbrains.geekbrainsspringdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.geekbrainsspringdata.configurations.jwt.JwtProvider;
import ru.geekbrains.geekbrainsspringdata.model.dtos.AuthRequestDto;
import ru.geekbrains.geekbrainsspringdata.model.dtos.AuthResponseDto;
import ru.geekbrains.geekbrainsspringdata.model.dtos.SignUpRequestDto;
import ru.geekbrains.geekbrainsspringdata.model.entities.User;
import ru.geekbrains.geekbrainsspringdata.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public String registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponseDto auth(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponseDto(token);
    }
}