package com.embarkx.firstjobapp.dto;


import com.embarkx.firstjobapp.job.model.Job;

import java.util.List;

public record CompanyResponse(
        String id,
        String name,
        String email,
        String description,
        String createdAt,
        String updatedAt,
        List<Job> jobs
) {
    public CompanyResponse update(
            String name,
            String email,
            String description,
            String updatedAt,
         List<Job>jobs) {
        return new CompanyResponse(
                this.id,
                name,
                email,
                description,
                this.createdAt,
                updatedAt,
                jobs
        );
    }
}
