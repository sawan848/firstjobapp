package com.embarkx.firstjobapp.dto;

import jakarta.validation.constraints.*;

public record JobRequest(
        @NotEmpty(message = "title cannot be null or empty")
        @Size(min = 5, max = 30, message = "The length of title should be between 5 and 30")
        String title,
        @NotEmpty(message = "Email cannot be null or empty")
        @Email(message = "Email address Should be valid value")
        String email,
        String description,
        @Min(message = "Minimum salary should be greater than 1000", value = 1000)
        Double minSalary,
        @Max(message = "Max salary should not be 0x7fffffff", value = Integer.MAX_VALUE)
        Double maxSalary,
        @NotEmpty(message = "location cannot be null or empty")
        String location
) {
}
