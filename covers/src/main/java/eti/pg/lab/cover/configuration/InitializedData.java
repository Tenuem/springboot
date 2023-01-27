package eti.pg.lab.cover.configuration;


import eti.pg.lab.cover.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final CoverService fileService;
    private final Environment environment;

    @Autowired
    public InitializedData(CoverService fileService, Environment environment) {
        this.fileService = fileService;
        this.environment = environment;
    }

    @PostConstruct
    private synchronized void init() {
        // room for initial data
    }
}