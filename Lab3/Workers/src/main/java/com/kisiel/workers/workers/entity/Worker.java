package com.kisiel.jobsAndWorkers.workers;

import com.kisiel.jobsAndWorkers.jobs.Job;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "workers")
public class Worker {

    @Id
    private String surname;

    private int age;

    private String name;

    @ManyToOne
    @JoinColumn(name = "job")
    private Job job;
}
