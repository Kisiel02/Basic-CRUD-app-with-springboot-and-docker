package com.kisiel.jobs.dto;

import com.kisiel.jobs.entity.Job;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetJobResponse {

    private String name;

    private int salary;

    private boolean studiesNeeded;

    public static Function<Job, GetJobResponse> entityToDtoMapper() {
        return job -> new GetJobResponse(job.getName(), job.getSalary(), job.isStudiesNeeded());
    }
}
