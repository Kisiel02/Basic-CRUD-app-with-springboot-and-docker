package com.kisiel.jobs.dto;

import com.kisiel.jobs.entity.Job;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetJobsResponse {

    private List<String> jobs;

    public static Function<Collection<Job>, GetJobsResponse> entityToDtoMapper() {
        return jobs -> new GetJobsResponse(
                jobs.stream()
                        .map(Job::getName)
                        .collect(Collectors.toList())
        );
    }
}
