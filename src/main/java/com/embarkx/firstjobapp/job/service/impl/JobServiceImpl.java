package com.embarkx.firstjobapp.job.service.impl;

import com.embarkx.firstjobapp.dto.JobRequest;
import com.embarkx.firstjobapp.dto.JobResponse;
import com.embarkx.firstjobapp.exception.JobNotFoundException;
import com.embarkx.firstjobapp.job.model.Job;
import com.embarkx.firstjobapp.job.repository.JobRepository;
import com.embarkx.firstjobapp.job.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.embarkx.firstjobapp.mapper.JobReuestMappper.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository repository;
    @Override
    public List<JobResponse> findAllJob() {
        return jobListToJobResponseList(repository.findAll());

    }

    @Override
    public JobResponse createJob(JobRequest job) {
      try{
          var format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
          String created=format.format(ZonedDateTime.now());
          var request=jobRequesterToJob(job,new Job());
          request.setId(UUID.randomUUID().toString());
          request.setCreatedAt(created);
          request.setUpdatedAt(created);
          log.info("created {}",request);
//          System.out.println("save = " + save);
          var save=repository.save(request);
         return JobToJobRequester(save);


      }catch (JobNotFoundException e){
          throw new JobNotFoundException("Job not created ");
      }

    }

    @Override
    public JobResponse updateJob(String jobID, JobRequest job) {
        try {
            var format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
            String updated=format.format(ZonedDateTime.now());

            var response=findByJobId(jobID);
            if (response!=null){
              JobResponse updateResponse =  response.update(job.title(),
                        job.email(),
                        job.description(),
                        job.maxSalary(),
                        job.minSalary(),
                        job.location(),
                        updated);
              var OriginalResponse=repository.save(
              JobResponseToJob(updateResponse));
                log.info("created {}",response);

                return JobToJobRequester(OriginalResponse);
//                repository.save(response);

            }
            throw new JobNotFoundException("Job is not updated with id "+jobID);
        }catch (JobNotFoundException e){
            throw new JobNotFoundException("Job is not updated with id "+jobID);

        }


    }

    @Override
    public JobResponse findByJobId(String jobID) {
        Job job = repository.
                findById(jobID).
                orElseThrow(() -> new JobNotFoundException("job is not found with id" + jobID));

        return JobToJobRequester(job);
    }

    @Override
    public boolean deleteJobById(String jobID) {
        Iterator<JobResponse> iterator=findAllJob().iterator();
        while (iterator.hasNext()){
            JobResponse response=iterator.next();
            if (response.id().equals(jobID)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public JobResponse findByMaxSalary(double salary) {
        return JobToJobRequester(repository.findByMaxSalary(salary));
    }

    @Override
    public JobResponse findByMinSalary(double salary) {
        return JobToJobRequester(repository.findByMinSalary(salary));

    }

    @Override
    public JobResponse findByLocation(String location) {
        return JobToJobRequester(repository.findByLocation(location));

    }
}
