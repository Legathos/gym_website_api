package com.gym_website.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "workouts")
public class WorkoutsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "workouts_id_seq")
    @SequenceGenerator(name = "workouts_id_seq", sequenceName = "workouts_id_seq",allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "total_weight")
    private Long total_weight;

    @Column(name = "total_time")
    private Long total_time;

}
