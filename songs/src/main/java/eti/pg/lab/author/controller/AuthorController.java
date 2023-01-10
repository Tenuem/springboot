package eti.pg.lab.author.controller;

import eti.pg.lab.song.service.SongService;
import eti.pg.lab.author.dto.CreateAuthorRequest;
import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
    private AuthorService authorService;
    private SongService songService;

    @Autowired
    public AuthorController(AuthorService authorService, SongService songService){
        this.authorService = authorService;
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<Void> createAuthorByPost(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder){
        Author author  = CreateAuthorRequest
                .dtoToEntityMapper().apply(request);

        author = authorService.create(author);

        return ResponseEntity
                .created(builder
                        .pathSegment("api", "authors", "{author_id}")
                        .buildAndExpand(author.getId()).toUri())
                .build();
    }

    @DeleteMapping("{author_id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("author_id") int id){
        Optional<Author> pilotToDelete = authorService.find(id);
        if(pilotToDelete.isPresent()){
            authorService.delete(id);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

