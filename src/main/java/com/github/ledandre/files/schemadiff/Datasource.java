package com.github.ledandre.files.schemadiff;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.github.ledandre.files.FileLoader;

public class Datasource {
    private final File file;

    public Datasource(String filename) {
        this.file = FileLoader.loadFile(filename);
    }

    public Set<String> getTableNames() {
        Set<String> tableNames = new HashSet<>();
        try {
            List<String> lines = FileLoader.getLinesAsList(file, "UTF-8");
            lines.forEach(tableNames::add);

        } catch (Exception e) {
            System.out.println("Erro ao obter nomes das tabelas: " + e.getMessage());
        }

        return tableNames;
    }
}
