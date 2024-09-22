package com.embarkx.firstjobapp.job.controller;

import com.embarkx.firstjobapp.dto.JobRequest;
import com.embarkx.firstjobapp.dto.JobResponse;
import com.embarkx.firstjobapp.job.model.Job;
import com.embarkx.firstjobapp.job.service.JobService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
@Validated
public class JobController {
    private final JobService service;


    @PostMapping("/save")
    public ResponseEntity<JobResponse> createJob(@RequestBody @Valid JobRequest job) {
        return new ResponseEntity<>(service.createJob(job), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobResponse>> getAllJob() {
        return ResponseEntity.of(Optional.ofNullable(service.findAllJob()));
    }

    @GetMapping("/max/{maxSalary}")
    public ResponseEntity<JobResponse> getMAXSalryJob(@PathParam("maxSalary")
                                                      @NotNull(message = "Salary cannot be empty")

                                                      Double maxSalary) {
        return ResponseEntity.ok(service.findByMaxSalary(maxSalary));
    }

    @GetMapping("/min/{minSalary}")
    public ResponseEntity<JobResponse> getAllJob(@PathParam("minSalary")
                                                     @Min(value = 1000, message = "Salary must be greater than 1000")

                                                 Double minSalary) {
        return ResponseEntity.ok(service.findByMinSalary(minSalary));
    }

    @PutMapping("/{jobID}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable
                                                 @NotNull(message = "Job ID cannot be empty")
                                                     String jobID,
                                                @RequestBody  @Valid JobRequest request) {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(service.updateJob(jobID,request));
    }
    @GetMapping("/{jobID}")
    public ResponseEntity<JobResponse> getJobByID(@PathVariable
                                                 @NotNull(message = "Job ID cannot be empty")

                                                     String jobID) {
        return ResponseEntity.ok(service.findByJobId(jobID));
    }
    @DeleteMapping("/{jobID}")
    public ResponseEntity<?> deleteJob(@PathVariable
                                                 @NotNull(message = "Job ID cannot be empty")

                                           String jobID) {
        var delted=service.deleteJobById(jobID);
        if (delted){
            return new ResponseEntity<>("Job deleted",HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
