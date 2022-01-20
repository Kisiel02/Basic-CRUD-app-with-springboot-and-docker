package com.kisiel.workers.workers.rest;

import com.kisiel.workers.jobs.entity.Job;
import com.kisiel.workers.jobs.rest.JobRepository;
import com.kisiel.workers.workers.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService{

    private final WorkerRepository workerRepository;

    private final JobRepository jobRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, JobRepository jobRepository) {
        this.workerRepository = workerRepository;
        this.jobRepository = jobRepository;
    }

    
    public Optional<Worker> find(String surname) {
        return workerRepository.findById(surname);
    }

    public Optional<Worker> find(Job job, String surname) {
        return workerRepository.findAllBySurnameAndJob(surname, job);
    }

    public Optional<Worker> find(String jobName, String surname) {
        Optional<Job> job = jobRepository.findById(jobName);
        if (job.isPresent()) {
            return workerRepository.findAllBySurnameAndJob(surname, job.get());
        } else {
            return Optional.empty();
        }
    }

    
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public List<Worker> findAll(Job job) {
        return workerRepository.findAllByJob(job);
    }

    
    public Worker create(Worker worker) {
        return workerRepository.save(worker);
    }

    
    public void delete(String surname) {
        workerRepository.delete(workerRepository.findById(surname).orElseThrow());
    }

    public void update(Worker worker) {
        workerRepository.save(worker);
    }
}
