package com.kisiel.jobs.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "jobs")
public class Job {

    @Id
    @Column(name = "job_name")
    private String name;

    private int salary;

    private boolean studiesNeeded;
}

