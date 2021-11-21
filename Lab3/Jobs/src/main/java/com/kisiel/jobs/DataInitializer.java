package com.kisiel.jobs;

import com.kisiel.jobs.entity.Job;
import com.kisiel.jobs.rest.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final JobService jobService;

    @Autowired
    public DataInitializer(JobService jobService) {
        this.jobService = jobService;
    }

    @PostConstruct
    private synchronized void init() {

        if (jobService.find("Doctor").isEmpty()) {
            Job doctor = new Job();
            doctor.setName("Doctor");
            doctor.setSalary(7000);
            doctor.setStudiesNeeded(true);
            jobService.create(doctor);
        }

        if (jobService.find("Builder").isEmpty()) {
            Job builder = new Job();
            builder.setName("Builder");
            builder.setSalary(3200);
            builder.setStudiesNeeded(false);
            jobService.create(builder);
        }

        if (jobService.find("Cleaner").isEmpty()) {
            Job cleaner = new Job();
            cleaner.setName("Cleaner");
            cleaner.setSalary(7000);
            cleaner.setStudiesNeeded(false);
            jobService.create(cleaner);
        }
    }
}
