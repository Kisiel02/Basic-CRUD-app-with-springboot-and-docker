package com.kisiel.workers.jobs.dto;

import com.kisiel.workers.jobs.entity.Job;
import lombok.*;

import java.util.Collections;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateJobRequest {

    private String name;

    public static Function<CreateJobRequest, Job> dtoToEntityMapper() {
        return request -> new Job(request.getName(), Collections.emptyList());
    }
}
