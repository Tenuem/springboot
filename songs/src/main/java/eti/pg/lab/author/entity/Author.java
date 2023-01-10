package eti.pg.lab.author.entity;

import eti.pg.lab.song.entity.Song;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
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

    @Id
    @Column(unique = true)
    private int id;

    /**
     List of songs made by author
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Song> songsList;



}
