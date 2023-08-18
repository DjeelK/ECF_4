package com.example.authentication.service;

import com.example.authentication.entity.User;
import com.example.authentication.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void testRegisterUser() {
        String username = "testuser";
        String password = "testpassword";
        String encodedPassword = "encodedpassword";

        User expectedUser = new User(1L, username, encodedPassword);

        Mockito.when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUser);

        User actualUser = userService.registerUser(username, password);

        Assertions.assertEquals(expectedUser, actualUser);
    }
}
