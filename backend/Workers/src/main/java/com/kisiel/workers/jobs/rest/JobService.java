package com.kisiel.workers.jobs.rest;

import com.kisiel.workers.jobs.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Optional<Job> find(String name) {
        return jobRepository.findById(name);
    }


    public Job create(Job job) {
        return jobRepository.save(job);
    }

    public void delete(String name) {
        jobRepository.delete(jobRepository.findById(name).orElseThrow());
    }
}
