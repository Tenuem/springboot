package eti.pg.lab.song.dto;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.song.entity.Song;
import lombok.*;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateSongRequest {
    private String title;
    private LocalDate dateOfRelease;
    private int time;
    private int authorId;

    public static BiFunction<Song, UpdateSongRequest, Song> dtoToEntityUpdater(
            Function<Integer, Author> authorFunction
    ){
        return (song, request) -> {
            song.setTitle(request.getTitle());
            song.setDateOfRelease(request.getDateOfRelease());
            song.setTime(request.getTime());
            song.setAuthor(authorFunction.apply(request.getAuthorId()));
            return song;
        };
    }
}
