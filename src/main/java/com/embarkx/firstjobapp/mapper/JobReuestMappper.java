package com.embarkx.firstjobapp.mapper;

import com.embarkx.firstjobapp.dto.JobRequest;
import com.embarkx.firstjobapp.dto.JobResponse;
import com.embarkx.firstjobapp.job.model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobReuestMappper {

    public static List<JobResponse> jobListToJobResponseList(List<Job> jobList){
        return jobList.stream().map(job -> new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getEmail(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation(),
                job.getCreatedAt(),
                job.getUpdatedAt()
        )).collect(Collectors.toList());



    }
    public static JobResponse jobAndJobRequesterToJobResponse(Job job, JobRequest jobRequest){
        JobResponse response=null;
        return response;
    }


    public static Job JobResponseToJob(JobResponse job){
        return new Job(
                job.id(),
                job.title(),
                job.email(),
                job.description(),
                job.minSalary(),
                job.maxSalary(),
                job.location(),
                job.createdAt(),
                job.updatedAt()
        );
    }
    public static JobResponse JobToJobRequester(Job job){
        return new JobResponse(job.getId(),
                job.getTitle(),
                job.getEmail(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation(),
                job.getCreatedAt(),
                job.getUpdatedAt()
        );
    }

    public static Job jobRequesterToJob(JobRequest request,Job job){
        job.setTitle(request.title());
        job.setEmail(request.email());
        job.setDescription(request.description());
        job.setMaxSalary(request.maxSalary());
        job.setMinSalary(request.minSalary());
        job.setLocation(request.location());
        return job;
    }
}
