package eti.pg.lab.configuration;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.service.AuthorService;
import eti.pg.lab.song.entity.Song;
import eti.pg.lab.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitializedData {

    private final AuthorService authorService;
    private final SongService songService;


    @Autowired
    public InitializedData(AuthorService authorService, SongService songService){
        this.authorService = authorService;
        this.songService = songService;
    }

    @PostConstruct
    private synchronized void init(){


        Author p1 = Author.builder()
                .id(1)
                .build();

        Author p2 = Author.builder()
                .id(2)
                .build();

        Author p3 = Author.builder()
                .id(3)
                .build();

        authorService.create(p1);
        authorService.create(p2);
        authorService.create(p3);


        Song s1 = Song.builder()
                .id(1)
                .title("Gargamon")
                .dateOfRelease(LocalDate.of(2011, 6, 1))
                .time(243)
                .author(p1)
                .build();

        Song s2 = Song.builder()
                .id(2)
                .title("My feast is heavy")
                .dateOfRelease(LocalDate.of(2018, 3,19))
                .time(234)
                .author(p1)
                .build();

        songService.create(s1);
        songService.create(s2);

    }

}
