package com.kisiel.workers.workers.dto;

import com.kisiel.workers.workers.entity.Worker;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class GetWorkersResponse {

    private List<String> workers;

    public static Function<Collection<Worker>, GetWorkersResponse> entityToDtoMapper() {
        return workers -> new GetWorkersResponse(
                workers.stream()
                        .map(Worker::getSurname)
                        .collect(Collectors.toList()));
    }
}
