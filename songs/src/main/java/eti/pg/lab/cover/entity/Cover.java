package eti.pg.lab.cover.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Entity
@Table(name="covers")
public class Cover {
    @Id
    @Column
    private int id;
    @Column
    private String description;
    @Column
    private String author;
    @Column
    private String filename;
    @Column
    private String filePath;
    @Lob
    private byte[] file;
}
