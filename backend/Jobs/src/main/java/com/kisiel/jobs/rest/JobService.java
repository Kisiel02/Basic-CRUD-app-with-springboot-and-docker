package com.kisiel.jobs.rest;

import com.kisiel.jobs.events.rest.JobEventRepository;
import com.kisiel.jobs.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    private final JobEventRepository jobEventRepository;

    @Autowired
    public JobService(JobRepository jobRepository, JobEventRepository jobEventRepository) {
        this.jobRepository = jobRepository;
        this.jobEventRepository = jobEventRepository;
    }

    public Optional<Job> find(String name) {
        return jobRepository.findById(name);
    }
    
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    
    public Job create(Job job) {
        jobEventRepository.create(job);
        jobRepository.save(job);
        return jobRepository.save(job);
    }
    
    public void delete(Job job) {
        jobEventRepository.delete(job);
        jobRepository.delete(job);
    }

    public void update(Job job) {
        jobRepository.save(job);
    }
}
