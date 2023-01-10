package eti.pg.lab.song.dto;

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
public class GetSongResponse {

    private int id;
    private String title;
    private LocalDate dateOfRelease;
    private int time;
    private int authorId;

    public static Function<Song, GetSongResponse> entityToDtoMapper(){
        return song -> GetSongResponse.builder()
                .id(song.getId())
                .title(song.getTitle())
                .dateOfRelease(song.getDateOfRelease())
                .time(song.getTime())
                .authorId(song.getAuthor().getId())
                .build();
    }
}
