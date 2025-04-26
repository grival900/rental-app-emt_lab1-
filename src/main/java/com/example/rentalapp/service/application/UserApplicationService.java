package com.example.rentalapp.service.application;

import com.example.rentalapp.dto.LoginResponseDto;
import com.example.rentalapp.model.domain.User;
import com.example.rentalapp.dto.CreateUserDto;
import com.example.rentalapp.dto.UserDto;
import com.example.rentalapp.dto.LoginUserDto;
import com.example.rentalapp.security.JwtHelper;
import com.example.rentalapp.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationService{

    private final UserService userService;
    private final JwtHelper jwtHelper;


    public UserApplicationService(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    public Optional<UserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(UserDto.from(user));
    }

    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    public Optional<UserDto> findByUsername(String username) {
        return Optional.of(UserDto.from(userService.findByUsername(username)));
    }
}
