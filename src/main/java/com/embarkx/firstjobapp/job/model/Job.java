package com.embarkx.firstjobapp.job.model;

import com.embarkx.firstjobapp.company.model.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job implements Serializable {
    @Id
    private String id;
    private String title;
    private String email;
    private String description;
    private double minSalary;
    private double maxSalary;
    private String location;
    private String createdAt;
    private String updatedAt;

    @ManyToOne
    private  Company company;
}
