package com.kisiel.workers.workers.dto;

import com.kisiel.workers.workers.entity.Worker;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class UpdateWorkerRequest {

    private int age;

    private String name;

    public static BiFunction<Worker, UpdateWorkerRequest, Worker> dtoToEntityUpdater() {
        return (worker, request) -> {
            worker.setAge(request.getAge());
            worker.setName(request.getName());
            return worker;
        };
    }
}
