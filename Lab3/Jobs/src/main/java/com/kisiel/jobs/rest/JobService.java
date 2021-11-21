package com.kisiel.jobs.rest;

import com.kisiel.jobs.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService implements ServiceInterface<Job, String> {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Optional<Job> find(String name) {
        return jobRepository.findById(name);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job create(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void delete(String name) {
        jobRepository.delete(jobRepository.findById(name).orElseThrow());
    }

    public void update(Job job) {
        jobRepository.save(job);
    }
}
