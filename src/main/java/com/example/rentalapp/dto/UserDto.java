package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.User;
import com.example.rentalapp.model.enumerations.Role;

public record UserDto(String username, String name, String surname, Role role) {

    public static UserDto from(User user){
        return new UserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser(){
        return new User(username, name, surname, role.name());
    }
}

