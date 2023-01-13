package eti.pg.lab.cover.controller;

import eti.pg.lab.cover.dto.CreateCoverRequest;
import eti.pg.lab.cover.dto.GetCoverResponse;
import eti.pg.lab.cover.dto.GetCoversResponse;
import eti.pg.lab.cover.entity.Cover;
import eti.pg.lab.cover.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("{id}")
    public ResponseEntity<GetCoverResponse> getAuthor(@PathVariable("id") int id){
        return coverService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetCoverResponse.entityToDtoMapper().apply(value)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCoverByPost(@RequestBody CreateCoverRequest request, UriComponentsBuilder builder){
        Cover cover  = CreateCoverRequest
                .dtoToEntityMapper().apply(request);

        cover = coverService.create(cover);

        return ResponseEntity
                .created(builder
                        .pathSegment("api", "covers", "{cover_id}")
                        .buildAndExpand(cover.getId()).toUri())
                .build();
    }
}
