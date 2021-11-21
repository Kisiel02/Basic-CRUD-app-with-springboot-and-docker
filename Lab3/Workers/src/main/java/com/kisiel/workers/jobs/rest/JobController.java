package com.kisiel.workers.jobs.rest;

import com.kisiel.workers.jobs.dto.CreateJobRequest;
import com.kisiel.workers.jobs.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Void> createJob(@RequestBody CreateJobRequest request) {
        Job job = CreateJobRequest.dtoToEntityMapper().apply(request);
        Optional<Job> found = jobService.find(job.getName());
        if (found.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            jobService.create(job);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteJob(@PathVariable("name") String name) {
        Optional<Job> job = jobService.find(name);
        if (job.isPresent()) {
            jobService.delete(job.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
