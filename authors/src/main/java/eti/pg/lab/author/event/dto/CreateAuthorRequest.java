package eti.pg.lab.author.event.dto;


import eti.pg.lab.author.entity.Author;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateAuthorRequest {
    private int id;

    public static Function<Author, CreateAuthorRequest> entityToDtoMapper(){
        return author -> CreateAuthorRequest.builder().id(author.getId()).build();
    }
}
