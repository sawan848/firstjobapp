package com.embarkx.firstjobapp.company.model;

import com.embarkx.firstjobapp.job.model.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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


   @OneToMany(mappedBy = "company")
    private List<Job>jobs;
}
