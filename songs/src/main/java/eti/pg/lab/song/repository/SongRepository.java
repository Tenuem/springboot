package eti.pg.lab.song.repository;

import eti.pg.lab.song.entity.Song;
import eti.pg.lab.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    Optional<Song> findById(int id);
    Optional<Song> findByAuthorAndId(Author author, int id);
    List<Song> findAll();
    List<Song> findAllByAuthor(Author author);
}

