package eti.pg.lab.author.service;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository){
        this.repository = repository;
    }

    public Optional<Author> find(int id){
        return repository.findById(id);
    }
    public List<Author> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Author create(Author author){
        return repository.save(author);
    }
    @Transactional
    public void update(Author author){
        repository.save(author);
    }
    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }
}
