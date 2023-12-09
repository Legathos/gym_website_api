package com.gym_website.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "logger")
public class LoggerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "logger_id_seq")
    @SequenceGenerator(name = "logger_id_seq", sequenceName = "logger_id_seq",allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "food_id")
    private Long food_id;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "calories")
    private Long calories;

    @Column(name = "carbs")
    private Long carbs;

    @Column(name = "fats")
    private Long fats;

    @Column(name = "protein")
    private Long protein;


}
