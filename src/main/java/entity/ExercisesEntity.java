package entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "exercises")
public class ExercisesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "exercises_id_seq")
    @SequenceGenerator(name = "exercises_id_seq", sequenceName = "exercises_id_seq",allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;
}
