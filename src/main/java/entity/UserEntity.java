package entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_table_id_seq")
    @SequenceGenerator(name = "user_table_id_seq", sequenceName = "user_table_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "age")
    private Long age;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "height")
    private Long height;

    @Column(name = "gender")
    private String gender;
}
