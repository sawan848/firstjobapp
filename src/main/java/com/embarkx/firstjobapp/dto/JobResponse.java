package com.embarkx.firstjobapp.dto;


import com.embarkx.firstjobapp.company.model.Company;

public record JobResponse(
        String id,
        String title,
        String email,
        String description,
        double minSalary,
        double maxSalary,
        String location,
        String createdAt,
        String updatedAt,
        Company company
) {
    public JobResponse update(
            String title,
            String email,
            String description,
            double minSalary,
            double maxSalary,
            String location,
            String updatedAt,
            Company company) {
        return new JobResponse(
                this.id,
                title,
                email,
                description,
                minSalary,
                maxSalary,
                location,
                this.createdAt,
                updatedAt,
                company
        );
    }
}
