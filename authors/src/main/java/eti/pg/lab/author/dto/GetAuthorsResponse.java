package eti.pg.lab.author.dto;

import eti.pg.lab.author.entity.Author;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAuthorsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class AuthorEntry {
        private String name;
        private int id;
        private LocalDate dateOfBirth;
    }
    @Singular
    private List<AuthorEntry> authors;

    public static Function<Collection<Author>, GetAuthorsResponse> entityToDtoMapper(){
        return authors -> {
            GetAuthorsResponseBuilder response = GetAuthorsResponse.builder();

            authors.stream()
                    .map(author -> AuthorEntry.builder()
                            .name(author.getName())
                            .id(author.getId())
                            .dateOfBirth(author.getDateOfBirth())
                            .build())
                    .forEach(response::author);
            return response.build();
        };
    }
}

