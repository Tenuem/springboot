package eti.pg.lab.author.dto;

import eti.pg.lab.author.entity.Author;
import lombok.*;

import java.time.LocalDate;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class PutAuthorRequest {
    private String name;
    //private int time;
    private LocalDate dateOfRelease;

    public static BiFunction<Author, PutAuthorRequest, Author> dtoToEntityUpdater(){
        return (author, request) -> {
            author.setName(request.getName());
            //author.setTime(request.getTime());
            author.setDateOfBirth(request.getDateOfRelease());
            return author;
        };
    }
}