package eti.pg.lab.initializer;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.service.AuthorService;
import eti.pg.lab.song.entity.Song;
import eti.pg.lab.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class Initializer {

    private final AuthorService authorService;
    private final SongService songService;


    @Autowired
    public Initializer(AuthorService authorService, SongService songService){
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

        authorService.create(p1);
        authorService.create(p2);


        Song s1 = Song.builder()
                .id(1)
                .title("Gargamon")
                .dateOfRelease(LocalDate.of(2011, 6, 1))
                .time(243)
                .author(p1)
                .build();

        Song s2 = Song.builder()
                .id(2)
                .title("Skurwysyn")
                .dateOfRelease(LocalDate.of(2018, 3,19))
                .time(234)
                .author(p1)
                .build();

        songService.create(s1);
        songService.create(s2);

    }

}
