package com.kisiel.jobs;

import com.kisiel.jobs.entity.Job;
import com.kisiel.jobs.rest.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final JobRepository jobRepository;

    @Autowired
    public DataInitializer(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostConstruct
    private synchronized void init() {

        Job doctor = new Job();
        doctor.setName("Doctor");
        doctor.setSalary(7000);
        doctor.setStudiesNeeded(true);
        jobRepository.save(doctor);

        Job builder = new Job();
        builder.setName("Builder");
        builder.setSalary(3200);
        builder.setStudiesNeeded(false);
        jobRepository.save(builder);


        Job cleaner = new Job();
        cleaner.setName("Cleaner");
        cleaner.setSalary(7000);
        cleaner.setStudiesNeeded(false);
        jobRepository.save(cleaner);

    }
}
