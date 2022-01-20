package com.kisiel.jobs.dto;

import com.kisiel.jobs.entity.Job;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateJobRequest {

    private String name;

    private int salary;

    private boolean studiesNeeded;

    public static Function<CreateJobRequest, Job> dtoToEntityMapper() {
        return request -> new Job(request.getName(), request.getSalary(), request.isStudiesNeeded());
    }

}
