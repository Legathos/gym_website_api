package entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "user_intake")
public class UserIntakeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_intake_id_seq")
    @SequenceGenerator(name = "user_intake_id_seq", sequenceName = "user_intake_id_seq",allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "calories")
    private Long calories;

    @Column(name = "carbs")
    private Long carbs;

    @Column(name = "fats")
    private Long fats;

    @Column(name = "protein")
    private Long protein;

    @Column(name = "date")
    private Date date;
}
