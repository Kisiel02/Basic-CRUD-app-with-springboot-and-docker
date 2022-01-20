package com.kisiel.workers.workers.rest;

import com.kisiel.workers.jobs.entity.Job;
import com.kisiel.workers.workers.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, String> {

    Optional<Worker> findAllBySurnameAndJob(String surname, Job job);

    List<Worker> findAllByJob(Job job);
}
