package com.example.leaderboard.service;

import com.example.leaderboard.entity.User;
import com.example.leaderboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparingInt(User::getScore).reversed())
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User registerUser(User user) {
        user.setScore(0);
        user.setBadges(new TreeSet<>());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));
        user.setScore(score);
        Set<String> badges = new TreeSet<>();
        if (score >= 1 && score < 30) {
            badges.add("Code Ninja");
        }
        if (score >= 30 && score < 60) {
            badges.add("Code Champ");
        }
        if (score >= 60 && score <= 100) {
            badges.add("Code Master");
        }
        user.setBadges(badges);
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
