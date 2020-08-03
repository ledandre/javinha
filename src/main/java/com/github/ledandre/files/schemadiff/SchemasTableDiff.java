package com.github.ledandre.files.schemadiff;

import static java.util.Objects.isNull;

import java.util.Set;
import java.util.stream.Collectors;

public class SchemasTableDiff {
    private Set<String> datasource1Tables;
    private Set<String> datasource2Tables;
    private static SchemasTableDiff instance;

    public static SchemasTableDiff getInstance() {
        if (isNull(instance)) {
            instance = new SchemasTableDiff();
        }

        return instance;
    }

    public SchemasTableDiff from(Datasource source) {
        this.datasource1Tables = source.getTableNames();
        return this;
    }

    public Set<String> getTablesThatIsNotIn(Datasource source) {
        this.datasource2Tables = source.getTableNames();

        return datasource1Tables
                    .stream()
                    .filter(table -> !datasource2Tables.contains(table))
                    .collect(Collectors.toSet());
    }
}
