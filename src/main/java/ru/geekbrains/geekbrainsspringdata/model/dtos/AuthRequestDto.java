package ru.geekbrains.geekbrainsspringdata.model.dtos;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String password;
}
