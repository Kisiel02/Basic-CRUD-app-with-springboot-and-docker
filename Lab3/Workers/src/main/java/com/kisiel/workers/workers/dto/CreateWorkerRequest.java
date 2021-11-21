package com.kisiel.workers.workers.dto;


import com.kisiel.workers.jobs.entity.Job;
import com.kisiel.workers.workers.entity.Worker;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

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

    public static Function<CreateWorkerRequest, Worker> dtoToEntityMapper(
            Supplier<Job> jobSupplier
    ) {
        return request -> new Worker(
                request.getSurname(),
                request.getAge(),
                request.getName(),
                jobSupplier.get()
        );
    }
}
