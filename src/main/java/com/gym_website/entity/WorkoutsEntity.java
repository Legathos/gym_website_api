package com.gym_website.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "user_workout")
public class WorkoutsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_workout_id_seq")
    @SequenceGenerator(name = "user_workout_id_seq", sequenceName = "user_workout_id_seq",allocationSize = 1)
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
