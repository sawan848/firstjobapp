package com.embarkx.firstjobapp.Review.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review implements Serializable {
    @Id
    private String id;
    private String title;
    private String description;
    private double rating;
    private String createdAt;
    private String updatedAt;
}
