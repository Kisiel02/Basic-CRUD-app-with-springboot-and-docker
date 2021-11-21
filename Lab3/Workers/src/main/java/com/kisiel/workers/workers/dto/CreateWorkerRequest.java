package com.kisiel.jobsAndWorkers.workers.dto;

import com.kisiel.jobsAndWorkers.jobs.Job;
import com.kisiel.jobsAndWorkers.workers.Worker;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class CreateWorkerRequest {

    private String surname;

    private int age;

    private String name;

    private String job;

    public static Function<CreateWorkerRequest, Worker> dtoToEntityMapper(
            Function<String, Job> jobFunction
    ) {
        return request -> new Worker(
                request.getSurname(),
                request.getAge(),
                request.getName(),
                jobFunction.apply(request.getJob())
        );
    }
}
