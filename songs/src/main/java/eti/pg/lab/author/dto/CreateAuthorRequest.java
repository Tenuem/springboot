package eti.pg.lab.author.dto;


import eti.pg.lab.author.entity.Author;
import eti.pg.lab.song.entity.Song;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
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
    private List<Song> songList;

    public static Function<CreateAuthorRequest, Author> dtoToEntityMapper(){
        return request -> Author.builder()
                .id(request.getId())
                .build();
    }
}
