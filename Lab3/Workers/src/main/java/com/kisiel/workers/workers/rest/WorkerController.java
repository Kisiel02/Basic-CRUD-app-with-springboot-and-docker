package com.kisiel.workers.workers.rest;

import com.kisiel.workers.workers.dto.CreateWorkerRequest;
import com.kisiel.workers.workers.dto.GetWorkerResponse;
import com.kisiel.workers.workers.dto.GetWorkersResponse;
import com.kisiel.workers.workers.dto.UpdateWorkerRequest;
import com.kisiel.workers.workers.entity.Worker;
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

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
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
                    .dtoToEntityMapper(() ->null)
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
