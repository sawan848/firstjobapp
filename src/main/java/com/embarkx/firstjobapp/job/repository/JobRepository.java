package com.embarkx.firstjobapp.job.repository;

import com.embarkx.firstjobapp.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {
    Job findByMinSalary(double minSalary);
    Job findByMaxSalary(double maxSalary);
    Job findByLocation(String location);

}
