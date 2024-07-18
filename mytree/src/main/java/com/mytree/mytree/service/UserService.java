package com.mytree.mytree.service;

import com.mytree.mytree.jpa.UserRepository;
import com.mytree.mytree.model.DTOs.CreateUserDTO;
import com.mytree.mytree.model.DTOs.LoginUserDto;
import com.mytree.mytree.model.DTOs.RecoveryJwtTokenDto;
import com.mytree.mytree.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public UserService(UserRepository _userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this._userRepository = _userRepository;
        this._passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    public User createUser(CreateUserDTO payload) {
        Optional<User> checkEmail = _userRepository.findByEmail(payload.getEmail());

        if (checkEmail.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuario com esse email!");

        String encodedPassword = _passwordEncoder.encode(payload.getPassword());

        var user = new User(payload.getName(), payload.getEmail(), encodedPassword);
        return _userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return _userRepository.findAll();
    }

    public Optional<User> findUserById(Integer userId) {
        return _userRepository.findById(userId);
    }

    public Optional<User> findUserByEmail(String email) {
        return _userRepository.findByEmail(email);
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
}
