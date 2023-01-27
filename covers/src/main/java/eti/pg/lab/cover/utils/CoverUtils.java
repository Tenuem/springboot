package eti.pg.lab.cover.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CoverUtils {
    public static byte[] readAllBytes(String filePath) {
        try {
            return Files.readAllBytes(Path.of(filePath));
        } catch (IOException ignored) { }
        return new byte[0];
    }
}