package com.mytree.mytree.controller;

import com.mytree.mytree.model.DTOs.CreateUserDTO;
import com.mytree.mytree.model.DTOs.LoginUserDto;
import com.mytree.mytree.model.DTOs.RecoveryJwtTokenDto;
import com.mytree.mytree.model.User;
import com.mytree.mytree.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService _userService;

    public UserController(UserService _userService) {
        this._userService = _userService;
    }

    @PostMapping("create")
    public ResponseEntity createUser(@Valid @RequestBody CreateUserDTO payload) {
        var user = _userService.createUser(payload);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @GetMapping("list")
    public ResponseEntity<List<User>> findAllUsers() {
        var users = _userService.findAllUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@Valid @RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = _userService.authenticateUser(loginUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
