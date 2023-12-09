package com.gym_website.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "user_weight")
public class UserWeightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_weight_id_seq")
    @SequenceGenerator(name = "user_weight_id_seq", sequenceName = "user_weight_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "weight")
    private Long  weight;

    @Column(name = "date")
    private Date date;
}
