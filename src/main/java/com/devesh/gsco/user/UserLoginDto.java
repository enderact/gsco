package com.devesh.gsco.user;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDto(
        @NotEmpty
        Integer userId,
        @NotEmpty
        String password
) {
}
