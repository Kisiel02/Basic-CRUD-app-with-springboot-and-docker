package com.kisiel.workers;

import com.kisiel.workers.jobs.entity.Job;
import com.kisiel.workers.jobs.rest.JobService;
import com.kisiel.workers.workers.entity.Worker;
import com.kisiel.workers.workers.rest.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final WorkerService workerService;

    private final JobService jobService;

    @Autowired
    public DataInitializer(WorkerService workerService, JobService jobService) {
        this.workerService = workerService;
        this.jobService = jobService;
    }

    @PostConstruct
    private synchronized void init() {

        if (jobService.find("Doctor").isEmpty()) {
            Job doctor = new Job();
            doctor.setName("Doctor");
            jobService.create(doctor);
        }

        if (jobService.find("Builder").isEmpty()) {
            Job builder = new Job();
            builder.setName("Builder");
            jobService.create(builder);
        }

        if (jobService.find("Cleaner").isEmpty()) {
            Job cleaner = new Job();
            cleaner.setName("Cleaner");
            jobService.create(cleaner);
        }

        if (workerService.find("Kowalski").isEmpty()) {
            workerService.create(
                    new Worker("Kowalski", 45, "Jan", jobService.find("Builder").orElseThrow()));
        }

        if (workerService.find("Szmidt").isEmpty()) {
            workerService.create(
                    new Worker("Szmidt", 34, "Martin", jobService.find("Builder").orElseThrow())
            );
        }

        if (workerService.find("Nowak").isEmpty()) {
            workerService.create(
                    new Worker("Nowak", 30, "Adrian", jobService.find("Cleaner").orElseThrow()
                    ));
        }

        if (workerService.find("Pietroch").isEmpty()) {
            workerService.create(
                    new Worker("Pietroch", 27, "Arek", jobService.find("Doctor").orElseThrow())
            );
        }
    }
}
