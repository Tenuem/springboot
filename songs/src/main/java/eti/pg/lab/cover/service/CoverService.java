package eti.pg.lab.cover.service;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.repository.AuthorRepository;
import eti.pg.lab.cover.entity.Cover;
import eti.pg.lab.cover.repository.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }
}
