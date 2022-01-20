package com.kisiel.jobs.dto;

import com.kisiel.jobs.entity.Job;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class UpdateJobRequest {

    private int salary;

    private boolean studiesNeeded;

    public static BiFunction<Job, UpdateJobRequest, Job> dtoToEntityUpdater() {
        return (job, request) -> {
          job.setSalary(request.getSalary());
          job.setStudiesNeeded(request.isStudiesNeeded());
          return job;
        };
    }
}
