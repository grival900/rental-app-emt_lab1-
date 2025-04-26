package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.User;
import com.example.rentalapp.model.enumerations.Role;

public record CreateUserDto (String username,
                            String password,
                            String repeatPassword,
                            String name,
                            String surname,
                            Role role){
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
