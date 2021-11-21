package com.kisiel.jobs.events.dto;

import com.kisiel.jobs.entity.Job;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class CreateJobRequest {

    private String jobName;

    public static Function<Job, CreateJobRequest> entityToDtoMapper() {
        return entity -> new CreateJobRequest(entity.getName());
    }

}
