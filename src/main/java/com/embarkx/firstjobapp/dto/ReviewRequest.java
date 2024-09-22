package com.embarkx.firstjobapp.dto;

import jakarta.validation.constraints.*;

public record ReviewRequest(
        @NotEmpty(message = "title cannot be null or empty")
        @Size(min = 5, max = 30, message = "The length of title should be between 5 and 30")
        String title,
        String description,
        double rating

) {
}
