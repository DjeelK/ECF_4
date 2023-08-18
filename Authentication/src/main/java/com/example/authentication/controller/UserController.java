package com.example.authentication.controller;


import com.example.authentication.dto.LoginRequestDTO;
import com.example.authentication.dto.LoginResponseDTO;
import com.example.authentication.dto.RegisterRequestDTO;
import com.example.authentication.dto.RegisterResponseDTO;
import com.example.authentication.entity.User;
import com.example.authentication.security.JWTGenerator;
import com.example.authentication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;

    public UserController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(LoginResponseDTO.builder().token(generator.generateToken(authentication)).build());
        }catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            User userApp = userService.registerUser(registerRequestDTO.getUsername(), passwordEncoder.encode(registerRequestDTO.getPassword()));
            return ResponseEntity.ok(RegisterResponseDTO.builder().id(userApp.getId()).message("User created").build());
        }catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}