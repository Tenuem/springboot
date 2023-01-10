package eti.pg.lab.song.entity;

import eti.pg.lab.author.entity.Author;
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
@ToString
@EqualsAndHashCode
@Entity
@Table(name="songs")
public class Song implements Serializable {

    @Id
    @Column(unique = true, name="song_id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="time")
    private int time;

    @Column(name="date_of_release")
    private LocalDate dateOfRelease;

    @ManyToOne
    @JoinColumn(name="author")
    private Author author;

}
