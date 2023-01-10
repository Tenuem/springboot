package eti.pg.lab.song.controller;


import eti.pg.lab.song.dto.CreateSongRequest;
import eti.pg.lab.song.dto.GetSongResponse;
import eti.pg.lab.song.dto.GetSongsResponse;
import eti.pg.lab.song.dto.UpdateSongRequest;
import eti.pg.lab.song.entity.Song;
import eti.pg.lab.song.service.SongService;
import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/authors/{authorId}/songs")
public class AuthorSongsController
{
    private AuthorService authorService;
    private SongService songService;

    @Autowired
    public AuthorSongsController(AuthorService authorService, SongService songService){
        this.authorService = authorService;
        this.songService = songService;
    }


    @GetMapping
    public ResponseEntity<GetSongsResponse> getSongs(@PathVariable("authorId") int songId){
        Optional<Author> author = authorService.find(songId);
        return author.map(value -> ResponseEntity
                        .ok(GetSongsResponse.entityToDtoMapper()
                                .apply(songService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("{songId}")
    public ResponseEntity<GetSongResponse> getLicense(@PathVariable("authorId") int authorId,
                                                      @PathVariable("songId") int songId){
        //System.out.println("Get with ID");
        return songService.find(authorId, songId)
                .map(value -> ResponseEntity.ok(GetSongResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping
    public ResponseEntity<Void> createLicense(@PathVariable("authorId") int authorId,
                                              @RequestBody CreateSongRequest request,
                                              UriComponentsBuilder builder){
        //request.setPilotId(authorId);
        Optional<Author> pilot = authorService.find(authorId);
        if(pilot.isPresent()){
            Song songToAdd = CreateSongRequest
                    // lambda always returns pilot with Id from @PathVariable
                    .dtoToEntityMapper(id -> authorService.find(authorId).orElseThrow())
                    .apply(request);
            songToAdd = songService.create(songToAdd);
            return ResponseEntity.created(builder.pathSegment("api", "authors", "{authorId}", "songs", "{songId}")
                    .buildAndExpand(pilot.get().getId(), songToAdd.getId()).toUri()).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Void> createLicensePOST(@PathVariable("authorId") int authorId,
                                                  @RequestBody CreateSongRequest request,
                                                  UriComponentsBuilder builder){
        //request.setPilotId(authorId);
        Optional<Author> pilot = authorService.find(authorId);
        if(pilot.isPresent()){
            Song songToAdd = CreateSongRequest
                    // lambda always returns pilot with Id from @PathVariable
                    .dtoToEntityMapper(id -> authorService.find(authorId).orElseThrow())
                    .apply(request);
            songToAdd = songService.create(songToAdd);
            return ResponseEntity.created(builder.pathSegment("api", "authors", "{authorId}", "songs", "{songId}")
                    .buildAndExpand(pilot.get().getId(), songToAdd.getId()).toUri()).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{songId}")
    public ResponseEntity<Void> updateSong(@PathVariable ("authorId") int authorId,
                                           @RequestBody UpdateSongRequest request,
                                           @PathVariable("songId") int songId){
        //System.out.println("Put with ID");
        Optional<Song> updatedSong = songService.find(authorId, songId);
        if(updatedSong.isPresent()){
            UpdateSongRequest.dtoToEntityUpdater(id -> authorService.find(authorId).get()).apply(updatedSong.get(), request);
            songService.update(updatedSong.get());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAllSongs(@PathVariable("authorId") int authorId){
        Optional<Author> author = authorService.find(authorId);
        if(author.isPresent()){
            List<Song> toDelete = author.get().getSongsList();
            for(Song l : toDelete){
                songService.delete(l.getId());
            }
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{songId}")
    public ResponseEntity<Void> deleteLicense(@PathVariable("authorId") int authorId, @PathVariable("songId") int songId){
        Optional<Song> song = songService.find(authorId, songId);
        if(song.isPresent()){
            songService.delete(song.get().getId());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
