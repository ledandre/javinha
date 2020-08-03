package com.github.ledandre.files.textsearch;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class RepoNavigator {
    private String rootFolder;
    private Set<String> extensionsWhitelist;
    private Set<File> filteredFiles;
    private static RepoNavigator instance;

    private RepoNavigator() {
        filteredFiles = new HashSet<>();
    }

    public static RepoNavigator getInstance() {
        if (isNull(instance)) {
            instance = new RepoNavigator();
        }

        return instance;
    }

    public RepoNavigator searchInto(String rootFolder) {
        this.rootFolder = rootFolder;
        return this;
    }

    public RepoNavigator withFileExtensions(String extendsionsWhitelist) {
        this.extensionsWhitelist = new HashSet<>(
                asList(extendsionsWhitelist
                                .trim()
                                .split(",")));
        return this;
    }

    public Set<File> start() {
        if (isNull(rootFolder) || isNull(extensionsWhitelist)) throw new IllegalStateException("O diretório raíz e as extensões aceitas devem ser informadas.");

        File root = new File(this.rootFolder);
        if (!root.exists()) throw new IllegalArgumentException("O diretório raíz informado não foi encontrado: " + rootFolder);

        navigate(root);
        return filteredFiles;
    }

    private void navigate(File rootFolder) {
        for (File file : rootFolder.listFiles()) {
            if (file.isDirectory()) {
                navigate(file);
            }

            filter(file);
        }
    }

    private void filter(File file) {
        if (hasDesiredExtension(file)) {
            filteredFiles.add(file);
        }
    }

    private boolean hasDesiredExtension(File file) {
        int dotPosition = file.getName().lastIndexOf('.');
        if (dotPosition == -1) return false;

        String fileExtension = file.getName().substring(dotPosition);
        if (extensionsWhitelist.contains(fileExtension)) {
            return true;
        }

        return false;
    }
}
