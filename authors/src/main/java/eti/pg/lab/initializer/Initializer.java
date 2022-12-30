package eti.pg.lab.initializer;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class Initializer {

    private final AuthorService authorService;


    @Autowired
    public Initializer(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostConstruct
    private synchronized void init(){

        Author p1 = Author.builder()
                .name("Nirvana")
                .id(1)
                .dateOfBirth(LocalDate.of(1971,3,12))
                .build();

        Author p2 = Author.builder()
                .name("Wolfgang Amadeus Mozart")
                .id(2)
                .dateOfBirth(LocalDate.of(1801, 4, 23))
                .build();

        Author p3 = Author.builder()
                .name("Shakira")
                .id(3)
                .dateOfBirth(LocalDate.of(1981, 9, 3))
                .build();

        authorService.create(p1);
        authorService.create(p2);
        authorService.create(p3);

    }

}
