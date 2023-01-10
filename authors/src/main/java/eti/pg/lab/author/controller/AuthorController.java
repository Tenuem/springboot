package eti.pg.lab.author.controller;

import eti.pg.lab.author.dto.CreateAuthorRequest;
import eti.pg.lab.author.dto.GetAuthorResponse;
import eti.pg.lab.author.dto.GetAuthorsResponse;
import eti.pg.lab.author.dto.PutAuthorRequest;
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

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetAuthorsResponse> getAuthors(){
        return ResponseEntity.ok(GetAuthorsResponse.entityToDtoMapper().apply(authorService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable("id") int id){
        return authorService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetAuthorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSongByPost(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder){
        Author author = CreateAuthorRequest
                .dtoToEntityMapper().apply(request);

        authorService.create(author);



        return ResponseEntity
                .created(builder
                        .pathSegment("api", "authors", "{id}")
                        .buildAndExpand(author.getId()).toUri())
                .build();
    }

    @PutMapping
    public ResponseEntity<Void> createAuthor(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder){
        Author author = CreateAuthorRequest
                .dtoToEntityMapper().apply(request);

        authorService.create(author);

        return ResponseEntity
                .created(builder
                        .pathSegment("api", "authors", "{id}")
                        .buildAndExpand(author.getId()).toUri())
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAuthor(@RequestBody PutAuthorRequest request, @PathVariable("id") int id){
        Optional<Author> updatedSong = authorService.find(id);
        if(updatedSong.isPresent()){
            PutAuthorRequest.dtoToEntityUpdater().apply(updatedSong.get(), request);
            authorService.update(updatedSong.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") int id){
        Optional<Author> deletedSong = authorService.find(id);
        if(deletedSong.isPresent()){
            authorService.delete(deletedSong.get());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

