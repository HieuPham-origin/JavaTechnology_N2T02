package com.example.lab9.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserDto {

    private int id;

    private String username;

    private String password;

    public UserDto(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
