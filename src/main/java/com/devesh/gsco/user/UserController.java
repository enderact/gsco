package com.devesh.gsco.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody UserLoginDto userLoginDto) {
        return userService.validCredentials(userLoginDto.userId(), userLoginDto.password());
    }

    @PatchMapping("/{user-id}/update/name")
    public void updateName(@PathVariable("user-id") int userId, @RequestBody Map<String, String> body) {
        userService.updateName(userId, body.get("name"));
    }

    @PatchMapping("/{user-id}/update/password")
    public void updatePassword(@PathVariable("user-id") int userId, @RequestBody Map<String, String> body) {
        userService.updatePassword(userId, body.get("password"));
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
