package eti.pg.lab.author.controller;

import eti.pg.lab.author.dto.GetAuthorResponse;
import eti.pg.lab.author.dto.GetAuthorsResponse;
import eti.pg.lab.author.dto.UpdateAuthorRequest;
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

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAuthor(@RequestBody UpdateAuthorRequest request, @PathVariable("id") int id){

        Optional<Author> authorToUpdate = authorService.find(id);

        if(authorToUpdate.isPresent()){
            UpdateAuthorRequest.dtoToEntityUpdater().apply(authorToUpdate.get(), request);
            authorService.update(authorToUpdate.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{author_id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("author_id") int id){

        Optional<Author> authorToDelete = authorService.find(id);

        if(authorToDelete.isPresent()){
            authorService.delete(id);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

