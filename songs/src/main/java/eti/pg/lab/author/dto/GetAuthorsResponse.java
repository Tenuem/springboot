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
    public static class Authority{
        private int id;
        private String name;
        private LocalDate dateOfBirth;
    }
    @Singular
    private List<Authority> authors;

    public static Function<Collection<Author>, GetAuthorsResponse> entityToDtoMapper(){
        return authors -> {
            GetAuthorsResponseBuilder response = GetAuthorsResponse.builder();

            authors.stream()
                    .map(author -> Authority.builder()
                            .name(author.getName())
                            .id(author.getId())
                            .dateOfBirth(author.getDateOfBirth())
                            .build())
                    .forEach(response::author);
            return response.build();
        };
    }
}
