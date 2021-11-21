package com.kisiel.jobsAndWorkers.jobs;

import com.kisiel.jobsAndWorkers.jobs.dto.CreateJobRequest;
import com.kisiel.jobsAndWorkers.jobs.dto.GetJobResponse;
import com.kisiel.jobsAndWorkers.jobs.dto.GetJobsResponse;
import com.kisiel.jobsAndWorkers.jobs.dto.UpdateJobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<GetJobsResponse> getJobs() {
        List<Job> jobs = jobService.findAll();
        Function<Collection<Job>, GetJobsResponse> mapper = GetJobsResponse.entityToDtoMapper();
        GetJobsResponse response = mapper.apply(jobs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetJobResponse> getJob(@PathVariable("name") String name) {
        return jobService.find(name)
                .map(value -> ResponseEntity.ok(GetJobResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("{name}")
    public ResponseEntity<Void> updateJob(@RequestBody UpdateJobRequest request, @PathVariable("name") String name) {
        Optional<Job> job = jobService.find(name);
        if (job.isPresent()) {
            UpdateJobRequest.dtoToEntityUpdater().apply(job.get(), request);
            jobService.update(job.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
