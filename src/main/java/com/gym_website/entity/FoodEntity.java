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

    @Column(name = "barcode")
    private Long barcode;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "calories")
    private Long calories;

    @Column(name = "carbs")
    private Double carbs;

    @Column(name = "fats")
    private Double fats;

    @Column(name = "protein")
    private Double protein;
}
