package com.gym_website.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "set_tracking")
public class SetTrackingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "workouts_id_seq")
    @SequenceGenerator(name = "workouts_id_seq", sequenceName = "workouts_id_seq",allocationSize = 1)
    private Long id;

    @Column(name = "workout_id")
    private Long workout_id;

    @Column(name = "exercise_id")
    private Long exercise_id;

    @Column(name = "set_id")
    private Long set_id;

    @Column(name = "set_number")
    private Long set_number;

    @Column(name = "reps")
    private Long reps;

    @Column(name = "weight")
    private Long weight;
}
