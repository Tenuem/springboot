package eti.pg.lab.song.controller;

import eti.pg.lab.song.dto.CreateSongRequest;
import eti.pg.lab.song.dto.GetLicenseResponse;
import eti.pg.lab.song.dto.GetSongsResponse;
import eti.pg.lab.song.dto.UpdateSongRequest;
import eti.pg.lab.song.entity.Song;
import eti.pg.lab.song.service.SongService;
import eti.pg.lab.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/songs")
public class SongController {
    private AuthorService authorService;
    private SongService songService;

    @Autowired
    public SongController(AuthorService authorService, SongService songService){
        this.authorService = authorService;
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<GetSongsResponse> getSongs(){
        return ResponseEntity.ok(GetSongsResponse.entityToDtoMapper().apply(songService.findAll()));
    }


    @GetMapping("{song_id}")
    public ResponseEntity<GetLicenseResponse> getSong(@PathVariable("song_id") int id){
        return songService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetLicenseResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Void> createSong(@RequestBody CreateSongRequest request, UriComponentsBuilder builder){
        Song songToAdd = CreateSongRequest.dtoToEntityMapper(id -> authorService.find(id).orElseThrow()).apply(request);
        songToAdd = songService.create(songToAdd);

        return ResponseEntity.created(builder
                .pathSegment("api", "songs", "{song_id}")
                .buildAndExpand(songToAdd.getId()).toUri())
            .build();
    }

    @PostMapping
    public ResponseEntity<Void> createSongByPost(@RequestBody CreateSongRequest request, UriComponentsBuilder builder){
        Song songToAdd = CreateSongRequest.dtoToEntityMapper(id -> authorService.find(id).orElseThrow()).apply(request);
        songToAdd = songService.create(songToAdd);

        return ResponseEntity.created(builder
                        .pathSegment("api", "songs", "{song_id}")
                        .buildAndExpand(songToAdd.getId()).toUri())
                .build();

    }

    @PutMapping("{song_id}")
    public ResponseEntity<Void> updateSong(@RequestBody UpdateSongRequest request, @PathVariable("song_id") int id){
        Optional<Song> updatedSong = songService.find(id);

        if(updatedSong.isPresent()){
            UpdateSongRequest.dtoToEntityUpdater(songId -> authorService.find(songId).get()).apply(updatedSong.get(), request);
            songService.update(updatedSong.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{song_id}")
    public ResponseEntity<Void> deleteSong(@PathVariable("song_id") int id){
        Optional<Song> deletedSong = songService.find(id);
        if(deletedSong.isPresent()){
            songService.delete(id);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
