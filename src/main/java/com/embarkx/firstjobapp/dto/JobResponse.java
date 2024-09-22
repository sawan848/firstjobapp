package com.embarkx.firstjobapp.dto;


public record JobResponse(
        String id,
        String title,
        String email,
        String description,
        double minSalary,
        double maxSalary,
        String location,
        String createdAt,
        String updatedAt
) {
    public JobResponse update(
            String title,
            String email,
            String description,
            double minSalary,
            double maxSalary,
            String location,
            String updatedAt) {
        return new JobResponse(
                this.id,
                title,
                email,
                description,
                minSalary,
                maxSalary,
                location,
                this.createdAt,
                updatedAt
        );
    }
}
