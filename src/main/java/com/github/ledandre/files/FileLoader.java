package com.github.ledandre.files;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileLoader {

    public static File loadFile(String filename) {
        return new File(FileLoader.class
                .getClassLoader()
                .getResource(filename)
                .getFile());
    }

    public static List<String> getLinesAsList(File file, String encoding) throws IOException {
        List<String> lines = new ArrayList<>();

        try (Stream<String> linesStream = Files.lines(file.toPath(), Charset.forName(encoding))) {
            linesStream.forEach(lines::add);

        } catch (UncheckedIOException e) {
            e.printStackTrace();
            //do nothing :)
        } catch (Exception e1) {
            System.out.println("Erro ao carregar arquivo " + file.getName() + ":" + e1.getMessage());
            e1.printStackTrace();
            throw new IOException(e1);
        }
        return lines;
    }
}
