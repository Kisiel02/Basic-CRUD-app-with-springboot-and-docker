package com.kisiel.workers.jobs.entity;

import com.kisiel.workers.workers.entity.Worker;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

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

    @Lazy
    @OneToMany(mappedBy = "job", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Worker> workers;
}
