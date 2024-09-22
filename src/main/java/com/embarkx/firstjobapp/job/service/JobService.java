package com.embarkx.firstjobapp.job.service;

import com.embarkx.firstjobapp.dto.JobRequest;
import com.embarkx.firstjobapp.dto.JobResponse;

import java.util.List;

public interface JobService {
     List<JobResponse>findAllJob();
     JobResponse createJob(JobRequest job);
     JobResponse updateJob(String jobID,JobRequest job);
     JobResponse findByJobId(String jobID);
     boolean  deleteJobById(String jobID);
     JobResponse findByMaxSalary(double salary);
     JobResponse findByMinSalary(double salary);
     JobResponse findByLocation(String location);

}
