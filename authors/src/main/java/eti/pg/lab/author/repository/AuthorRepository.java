package eti.pg.lab.author.repository;

import eti.pg.lab.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAll();
}
