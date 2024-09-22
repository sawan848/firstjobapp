package com.embarkx.firstjobapp.dto;

import jakarta.validation.constraints.*;

public record CompanyRequest(
        @NotEmpty(message = "Name cannot be null or empty")
        @Size(min = 5, max = 30, message = "The length of title should be between 5 and 30")
        String name,
        @NotEmpty(message = "Email cannot be null or empty")
        @Email(message = "Email address Should be valid value")
        String email,
        String description
) {
}
