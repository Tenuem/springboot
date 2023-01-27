package eti.pg.lab.cover.controller;

import eti.pg.lab.cover.dto.CreateCoverRequest;
import eti.pg.lab.cover.dto.GetCoverResponse;
import eti.pg.lab.cover.dto.GetCoversResponse;
import eti.pg.lab.cover.entity.Cover;
import eti.pg.lab.cover.service.CoverService;
import eti.pg.lab.cover.utils.CoverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("api/covers")
public class CoverController {

    private CoverService coverService;

    @Autowired
    public CoverController(CoverService coverService){
        this.coverService = coverService;
    }

    @GetMapping
    public ResponseEntity<GetCoversResponse> getCovers(){
        return ResponseEntity.ok(GetCoversResponse.entityToDtoMapper().apply(coverService.findAll()));
    }

    @GetMapping(value="{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    /*
    public ResponseEntity<GetCoverResponse> getAuthor(@PathVariable("id") int id){
        return coverService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetCoverResponse.entityToDtoMapper().apply(value)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }*/
    public ResponseEntity<byte[]> getFile(@PathVariable("id") int id) {
        Optional<Cover> file = coverService.find(id);

        if (file.isEmpty()) {
            System.out.println("file was empty");
            return ResponseEntity.notFound().build();
        }

        Cover existingFile = file.get();

        return ResponseEntity.ok(CoverUtils.readAllBytes(existingFile.getFilePath()));
    }

    /*
    @PostMapping
    public ResponseEntity<Void> createCoverByPost(@RequestBody CreateCoverRequest request, UriComponentsBuilder builder){
        Cover cover  = CreateCoverRequest
                .dtoToEntityMapper().apply(request);

        cover = coverService.create(cover);

        return ResponseEntity
                .created(builder
                        .pathSegment("api", "covers", "{id}")
                        .buildAndExpand(cover.getId()).toUri())
                .build();
    }*/
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> createFile(@ModelAttribute CreateCoverRequest request, UriComponentsBuilder builder) {
        Cover newFile = CreateCoverRequest
                .dtoToEntityMapper()
                .apply(request);

        Optional<Cover> createdFile = coverService.create(newFile, request.getFile());

        return createdFile.map(file -> ResponseEntity
                .created(builder
                        .pathSegment("api", "covers", "{id}").buildAndExpand(file.getId()).toUri())
                .body(file.getId())).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
