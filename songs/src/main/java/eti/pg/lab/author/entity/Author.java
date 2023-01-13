package eti.pg.lab.author.entity;

import eti.pg.lab.song.entity.Song;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


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

    public Author(int id, String name, LocalDate date){
        this.id = id;
        this.name = name;
        this.dateOfBirth = date;
    }


    @Id
    @Column(unique = true)
    private int id;

    @Column
    private String name;

    @Column
    private LocalDate dateOfBirth;
    /**
     List of songs made by author
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Song> songsList;



}
