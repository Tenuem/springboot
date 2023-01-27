package eti.pg.lab.cover.repository;

import eti.pg.lab.cover.entity.Cover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileContentRepository {
    private final File storage;

    @Autowired
    public FileContentRepository(Environment environment) {
        this.storage = new File(/*environment.getProperty("coverStorage.path")*/"D:\\javalab\\ratunku\\covers\\src\\main\\resources\\files");
    }

    public String save(String name, MultipartFile content) throws IOException {
        byte[] bytes = content.getBytes();

        String location = storage.getPath() + "/" + name + ".jpg";
        //System.out.printf("SAVING FILE IN: %s\n", location);
        try (FileOutputStream stream = new FileOutputStream(location)) {
            stream.write(bytes);
        }

        return location;
    }
}
