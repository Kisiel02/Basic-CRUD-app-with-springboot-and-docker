package com.kisiel.jobsAndWorkers.workers;

import com.kisiel.jobsAndWorkers.jobs.Job;
import com.kisiel.jobsAndWorkers.jobs.JobService;
import com.kisiel.jobsAndWorkers.workers.dto.CreateWorkerRequest;
import com.kisiel.jobsAndWorkers.workers.dto.GetWorkerResponse;
import com.kisiel.jobsAndWorkers.workers.dto.GetWorkersResponse;
import com.kisiel.jobsAndWorkers.workers.dto.UpdateWorkerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/workers")
public class WorkerController {

    private WorkerService workerService;

    private JobService jobService;

    @Autowired
    public WorkerController(WorkerService workerService, JobService jobService) {
        this.workerService = workerService;
        this.jobService = jobService;
    }

    @GetMapping("{jobName}/workers")
    public ResponseEntity<GetWorkersResponse> getAllWorkersByJob(@PathVariable("jobName") String jobName) {
        Optional<Job> job = jobService.find(jobName);
        return job.map(value -> ResponseEntity.ok(GetWorkersResponse.entityToDtoMapper().apply(workerService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<GetWorkersResponse> getAllWorkers() {
        List<Worker> all = workerService.findAll();
        Function<Collection<Worker>, GetWorkersResponse> mapper = GetWorkersResponse.entityToDtoMapper();
        GetWorkersResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{surname}")
    public ResponseEntity<GetWorkerResponse> getWorker(@PathVariable("surname") String surname) {
        return workerService.find(surname)
                .map(value -> ResponseEntity.ok(GetWorkerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createWorker(@RequestBody CreateWorkerRequest request) {
        try {
            Worker worker = CreateWorkerRequest
                    .dtoToEntityMapper(name -> jobService.find(name).orElseThrow())
                    .apply(request);
            workerService.create(worker);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{surname}")
    public ResponseEntity<Void> deleteWorker(@PathVariable("surname") String surname) {
        Optional<Worker> worker = workerService.find(surname);
        if (worker.isPresent()) {
            workerService.delete(worker.get().getSurname());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{surname}")
    public ResponseEntity<Void> updateWorker(@RequestBody UpdateWorkerRequest request, @PathVariable("surname") String surname) {
        Optional<Worker> worker = workerService.find(surname);
        if (worker.isPresent()) {
            UpdateWorkerRequest.dtoToEntityUpdater().apply(worker.get(), request);
            workerService.update(worker.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
