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
public class CreateAuthorRequest {

    private String name;
    private int id;
    private LocalDate dateOfBirth;

    public static Function<CreateAuthorRequest, Author> dtoToEntityMapper(){
        return request -> Author.builder()
                .name(request.getName())
                .id(request.getId())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }
}

