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

public class UpdateAuthorRequest {
    private String name;
    private LocalDate dateOfBirth;

    public static BiFunction<Author, UpdateAuthorRequest, Author> dtoToEntityUpdater(){
        return (author, request) -> {
            author.setName(request.getName());
            author.setDateOfBirth(request.getDateOfBirth());
            return author;
        };
    }
}
