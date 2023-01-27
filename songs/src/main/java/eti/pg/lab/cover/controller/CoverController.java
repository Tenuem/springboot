package eti.pg.lab.cover.controller;

import eti.pg.lab.cover.dto.CreateCoverRequest;
import eti.pg.lab.cover.dto.GetCoverResponse;
import eti.pg.lab.cover.dto.GetCoversResponse;
import eti.pg.lab.cover.entity.Cover;
import eti.pg.lab.cover.service.CoverService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/covers")
public class CoverController {

    private CoverService coverService;

    @SneakyThrows
    private byte[] getResourceAsByteArray(String path){
        try (InputStream is = Files.newInputStream(Paths.get(path))) {
            //System.out.println(name);
            return is.readAllBytes();
        }
    }

    @Autowired
    public CoverController(CoverService coverService){
        this.coverService = coverService;
    }

    @GetMapping
    public ResponseEntity<GetCoversResponse> getCovers(){
        return ResponseEntity.ok(GetCoversResponse.entityToDtoMapper().apply(coverService.findAll()));
    }

    @GetMapping(value = "{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getAuthor(@PathVariable("id") int id){
        /*return coverService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetCoverResponse.entityToDtoMapper().apply(value)))
                .orElseGet(()->ResponseEntity.notFound().build());*/
        return coverService.find(id)
                .map(value -> ResponseEntity.ok()
                        .contentType(MediaType.valueOf("application/octet-stream"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + value.getFilename())
                        .body(getResourceAsByteArray("C:\\Users\\Adammo\\Desktop\\files\\" + value.getFilename().toString()) ))  // value.getStored_file()
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}/details")
    public ResponseEntity<GetCoverResponse> getCover(@PathVariable("id") int id) {
        return coverService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetCoverResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCoverByPost(/*@RequestBody CreateCoverRequest request,*/ @RequestParam("id") int id, @RequestParam("desc") String desc, @RequestParam("author") String author, @RequestParam("file") MultipartFile file, UriComponentsBuilder builder){

        CreateCoverRequest request = new CreateCoverRequest(desc, id, author);

        Cover cover  = CreateCoverRequest
                .dtoToEntityMapper().apply(request);


        cover.setFilename(file.getOriginalFilename());
        System.out.println("filename = " + file.getOriginalFilename());
        file.transferTo(new File("C:\\Users\\Adammo\\Desktop\\files\\" + file.getOriginalFilename()));

        byte[] filedata = file.getBytes();
        cover.setFile(filedata);

        cover = coverService.create(cover);

        return ResponseEntity
                .created(builder
                        .pathSegment("api", "covers", "{id}")
                        .buildAndExpand(cover.getId()).toUri())
                .build();
    }
}
