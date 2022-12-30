package eti.pg.lab.song.dto;

import eti.pg.lab.song.entity.Song;
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
public class GetSongsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode

    public static class SongEntry {
        private int id;
        private String title;
        private LocalDate dateOfRelease;
        private int time;
        private int authorId;
    }

    @Singular
    private List<SongEntry> songs;

    public static Function<Collection<Song>, GetSongsResponse> entityToDtoMapper(){
        return songs -> {
            GetSongsResponseBuilder response = GetSongsResponse.builder();
            songs.stream()
                    .map(song -> SongEntry.builder()
                            .id(song.getId())
                            .title(song.getTitle())
                            .dateOfRelease(song.getDateOfRelease())
                            .time(song.getTime())
                            .authorId(song.getAuthor().getId())
                            .build()
                    ).forEach(response::song);
            return response.build();
        };
    }
}
