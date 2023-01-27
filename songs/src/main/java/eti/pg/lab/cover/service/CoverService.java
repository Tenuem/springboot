package eti.pg.lab.cover.service;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.repository.AuthorRepository;
import eti.pg.lab.cover.entity.Cover;
import eti.pg.lab.cover.repository.CoverRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CoverService {
    private CoverRepository repository;

    @Autowired
    public CoverService(CoverRepository repository){
        this.repository = repository;
    }

    public Optional<Cover> find(int id){
        return repository.findById(id);
    }

    public List<Cover> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Cover create(Cover cover){
        return repository.save(cover);
    }/*
    @SneakyThrows
    @Transactional
    public Cover create(Cover cover){
        Path path = Paths.get("D:\\javalab\\spring\\songs\\src\\main\\resources\\files");
        File file = new File(path + "\\" + cover.getFilename());
        OutputStream os = new FileOutputStream(file);
        os.write(cover.getFile());

        return repository.save(cover);
    }*/

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }
}
