package eti.pg.lab.author.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Entity
@Table(name="authors")
public class Author implements Serializable {

    @Column(name = "author_name")
    private String name;

    @Id
    @Column(unique = true)
    private int id;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
}
