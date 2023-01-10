package eti.pg.lab.song.dto;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.song.entity.Song;
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
public class CreateSongRequest {

    private int id;
    private String title;
    private LocalDate dateOfRelease;
    private int time;
    private int authorId;


    public static Function<CreateSongRequest, Song> dtoToEntityMapper(
            Function<Integer, Author> authorFunction
    ){
        return request -> Song.builder()
                .id(request.getId())
                .title(request.getTitle())
                .dateOfRelease(request.getDateOfRelease())
                .time(request.getTime())
                .author(authorFunction.apply(request.getAuthorId()))
                .build();
    }
}
