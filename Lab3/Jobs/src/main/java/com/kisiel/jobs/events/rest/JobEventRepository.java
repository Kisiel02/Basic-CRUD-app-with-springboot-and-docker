package com.kisiel.jobs.events.rest;

import com.kisiel.jobs.events.dto.CreateJobRequest;
import com.kisiel.jobs.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class JobEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public JobEventRepository(@Value("http://localhost:8082/api/") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Job job) {
        restTemplate.delete("/jobs/{name}", job.getName());
    }

    public void create(Job job) {
        restTemplate.postForLocation("/jobs", CreateJobRequest.entityToDtoMapper().apply(job));
    }


}
