package com.github.ledandre.files.textsearch;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.github.ledandre.files.FileLoader;

public class Main {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String TABLES_TO_SEARCH_FILE_NAME = "tables_to_search.txt";
    private static TextSeeker textSeeker = new TextSeeker();
    private static Set<File> filesToSearch;
    private static Set<String> usedTables = new HashSet<>();
    private static int count = 0;
    private static String encoding = DEFAULT_ENCODING;

    public static void main(String[] args) throws IOException {
        String rootFolder = args[0];
        String desiredExtensions = args[1];

        if (args.length == 3) {
            encoding = args[2];
        }

        System.out.println("=====================================================================================");
        System.out.println(String.format("Iniciando leitura dos arquivos no diretório %s com as extensões %s", rootFolder, desiredExtensions));
        System.out.println("=====================================================================================");

        RepoNavigator repoNavigator = RepoNavigator.getInstance();

        filesToSearch = repoNavigator
                .searchInto(args[0])
                .withFileExtensions(args[1])
                .start();
        System.out.println("Arquivos filtrados com sucesso!");
        System.out.println("=====================================================================================");
        System.out.println("Iniciando busca por uso de tabelas...");

        File tableDiffFile = FileLoader.loadFile(TABLES_TO_SEARCH_FILE_NAME);
        List<String> tablesToSearch = FileLoader.getLinesAsList(tableDiffFile, encoding);

        for (String tableName : tablesToSearch) {
            System.out.println("Buscando a tabela " + tableName);
            searchIntoFiles(tableName);
        }
        System.out.println(
                String.format("Processo terminado com sucesso! Foram encontradas %d ocorrências de uso das tabelas informadas", count));
        System.out.println("=====================================================================================");
        usedTables.forEach(System.out::println);
        System.out.println("=====================================================================================");
    }

    private static void searchIntoFiles(String tableName) throws IOException {
        for (File file : filesToSearch) {
            searchTable(tableName, file.getAbsolutePath(), FileLoader.getLinesAsList(file, encoding));
        }
    }

    private static void searchTable(String tableName, String fileName, List<String> fileLines) throws IOException {
        for (String line : fileLines) {
            if(textSeeker.seek(tableName, line)) {
                usedTables.add(String.format("A tabela %s foi encontrada no arquivo %s", tableName, fileName));
                count++;
            }
        }
    }
}
