package com.embarkx.firstjobapp.company.model;

import com.embarkx.firstjobapp.job.model.Job;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String description;
    private String createdAt;
    private String updatedAt;

    @OneToMany
    private List<Job>jobs;
}
