package eti.pg.lab.song.service;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.song.entity.Song;
import eti.pg.lab.song.repository.SongRepository;
import eti.pg.lab.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private SongRepository songRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public SongService(SongRepository songRepository, AuthorRepository authorRepository){
        this.songRepository = songRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Song> find(int id){
        return songRepository.findById(id);
    }

    public Optional<Song> find(int pilotId, int id){
        Optional<Author> author = authorRepository.findById(pilotId);
        if(author.isPresent()){
            return songRepository.findByAuthorAndId(author.get(), id);
        }else{
            return Optional.empty();
        }

    }
    public List<Song> findAll(){
        return songRepository.findAll();
    }

    public List<Song> findAll(Author author){
        return songRepository.findAllByAuthor(author);
    }

    @Transactional
    public Song create(Song song){
        return songRepository.save(song);
    }
    @Transactional
    public void update(Song song){
        songRepository.save(song);
    }
    @Transactional
    public void delete(int id){
        songRepository.deleteById(id);
    }

}
