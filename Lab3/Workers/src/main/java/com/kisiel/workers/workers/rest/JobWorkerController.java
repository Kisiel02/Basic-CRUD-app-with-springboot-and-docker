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
@RequestMapping("api/jobs/{jobName}/workers")
public class WorkerJobController {

    private WorkerService workerService;

    private JobService jobService;

    @Autowired
    public WorkerController(WorkerService workerService, JobService jobService) {
        this.workerService = workerService;
        this.jobService = jobService;
    }

}
