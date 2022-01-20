package com.kisiel.workers.workers.rest;

import com.kisiel.workers.jobs.entity.Job;
import com.kisiel.workers.jobs.rest.JobService;
import com.kisiel.workers.workers.dto.CreateWorkerRequest;
import com.kisiel.workers.workers.dto.GetWorkerResponse;
import com.kisiel.workers.workers.dto.GetWorkersResponse;
import com.kisiel.workers.workers.dto.UpdateWorkerRequest;
import com.kisiel.workers.workers.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/jobs/{jobName}/workers")
public class JobWorkerController {

    private WorkerService workerService;

    private JobService jobService;

    @Autowired
    public JobWorkerController(WorkerService workerService, JobService jobService) {
        this.workerService = workerService;
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<GetWorkersResponse> getWorkers(@PathVariable("jobName") String jobName) {
        Optional<Job> job = jobService.find(jobName);
        return job.map(value -> ResponseEntity.ok(GetWorkersResponse.entityToDtoMapper().apply(workerService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{surname}")
    public ResponseEntity<GetWorkerResponse> getWorker(@PathVariable("jobName") String jobName,
                                                       @PathVariable("surname") String surname) {
        return workerService.find(jobName, surname)
                .map(value -> ResponseEntity.ok(GetWorkerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createWorker(@PathVariable("jobName") String jobName,
                                             @RequestBody CreateWorkerRequest request) {
        Optional<Job> job = jobService.find(jobName);
        if (job.isPresent()) {
            Worker worker = CreateWorkerRequest
                    .dtoToEntityMapper(job::get)
                    .apply(request);
            workerService.create(worker);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{surname}")
    public ResponseEntity<Void> deleteWorker(@PathVariable("jobName") String jobName,
                                             @PathVariable("surname") String surname) {
        Optional<Worker> worker = workerService.find(jobName, surname);
        if (worker.isPresent()) {
            workerService.delete(worker.get().getSurname());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{surname}")
    public ResponseEntity<Void> updateWorker(@PathVariable("jobName") String jobName,
                                             @RequestBody UpdateWorkerRequest request,
                                             @PathVariable("surname") String surname) {
        Optional<Worker> worker = workerService.find(jobName, surname);
        if (worker.isPresent()) {
            UpdateWorkerRequest.dtoToEntityUpdater().apply(worker.get(), request);
            workerService.update(worker.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
