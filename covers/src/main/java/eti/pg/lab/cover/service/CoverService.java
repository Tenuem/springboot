package eti.pg.lab.cover.service;

import eti.pg.lab.cover.entity.Cover;
import eti.pg.lab.cover.repository.CoverRepository;
import eti.pg.lab.cover.repository.FileContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CoverService {
    private CoverRepository repository;
    private FileContentRepository fileContentRepository;

    @Autowired
    public CoverService(CoverRepository repository, FileContentRepository fileContentRepository){
        this.repository = repository;
        this.fileContentRepository = fileContentRepository;
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
    public Optional<Cover> create(Cover file, MultipartFile content) {
        try {
            String filePath = fileContentRepository.save(file.getDescription(), content);
            file.setFilePath(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(repository.save(file));
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }
}
