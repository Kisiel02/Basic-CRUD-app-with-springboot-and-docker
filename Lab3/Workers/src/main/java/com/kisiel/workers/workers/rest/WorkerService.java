package com.kisiel.jobsAndWorkers.workers;

import com.kisiel.jobsAndWorkers.ServiceInterface;
import com.kisiel.jobsAndWorkers.jobs.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService implements ServiceInterface<Worker, String> {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Optional<Worker> find(String surname) {
        return workerRepository.findById(surname);
    }

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public List<Worker> findAll(Job job) {
        return workerRepository.findAllByJob(job);
    }

    @Override
    public Worker create(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public void delete(String surname) {
        workerRepository.delete(workerRepository.findById(surname).orElseThrow());
    }

    public void update(Worker worker) {
        workerRepository.save(worker);
    }
}
