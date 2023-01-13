package eti.pg.lab.author.dto;

import eti.pg.lab.author.entity.Author;
import lombok.*;

import java.time.LocalDate;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAuthorResponse {
    private String name;
    private int id;
    private LocalDate dateOfBirth;

    public static Function<Author, GetAuthorResponse> entityToDtoMapper(){
        return author -> GetAuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .dateOfBirth(author.getDateOfBirth())
                .build();
    }
}
