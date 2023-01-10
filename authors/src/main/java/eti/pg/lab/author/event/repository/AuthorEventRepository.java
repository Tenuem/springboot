package eti.pg.lab.author.event.repository;


import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.event.dto.CreateAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AuthorEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public AuthorEventRepository(@Value("${lab.songs.url}") String baseURL){
        restTemplate = new RestTemplateBuilder().rootUri(baseURL).build();
    }

    public void delete(Author author){
        restTemplate.delete("/authors/{id}", author.getId());
    }

    public void create(Author author){
        restTemplate.postForLocation("/authors", CreateAuthorRequest.entityToDtoMapper().apply(author));
    }

}
