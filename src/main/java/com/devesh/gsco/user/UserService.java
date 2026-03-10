package com.devesh.gsco.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean validCredentials(Integer userId, String password) {
        return userRepository.findByUserId(userId)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    public void updateName(Integer userId, String name) {
        userRepository.updateName(name, userId);
    }
    public void updatePassword(Integer userId, String password) {
        userRepository.updatePassword(password, userId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
