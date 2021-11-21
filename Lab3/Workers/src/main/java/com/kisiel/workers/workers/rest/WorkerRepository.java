package com.kisiel.jobsAndWorkers.workers;

import com.kisiel.jobsAndWorkers.jobs.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, String> {

    List<Worker> findAllByJob(Job job);
}
