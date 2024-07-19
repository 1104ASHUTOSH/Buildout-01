package com.example.leaderboard.service;

import com.example.leaderboard.entity.User;
import com.example.leaderboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId("1");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        
        Optional<User> result = userService.getUserById("1");
        
        assertEquals(user, result.orElse(null));
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUserId("1");
        user.setUsername("Ashutosh");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);

        assertEquals(0, result.getScore());
        assertEquals(user, result);
    }

    // More test cases can be added here...
}
