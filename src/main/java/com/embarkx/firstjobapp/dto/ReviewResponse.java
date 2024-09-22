package com.embarkx.firstjobapp.dto;


public record ReviewResponse(
         String id,

        String title,
        String description,
        double rating,
           String createdAt,
           String updatedAt

) {
    public ReviewResponse update(
            String title,
            String description,
            double rating,
            String updatedAt) {
        return new ReviewResponse(
                this.id,
                title,
                description,
                rating,
                this.createdAt,
                updatedAt
        );
    }
}
