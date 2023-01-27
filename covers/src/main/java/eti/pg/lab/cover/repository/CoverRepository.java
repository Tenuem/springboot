package eti.pg.lab.cover.repository;

import eti.pg.lab.cover.entity.Cover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Repository
public interface CoverRepository extends JpaRepository<Cover, Integer> {
    List<Cover> findAll();
    void deleteById(UUID id);
}
