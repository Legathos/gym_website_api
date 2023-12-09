package com.gym_website.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "food")
public class FoodEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "food_id_seq")
    @SequenceGenerator( name = "food_id_seq", sequenceName = "food_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

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
