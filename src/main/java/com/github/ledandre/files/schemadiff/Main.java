package com.github.ledandre.files.schemadiff;

import static java.lang.String.format;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("=====================================================================================");
        System.out.print("Iniciando leitura das tabelas do schema " + args[0]);
        Datasource datasource1 = new Datasource(args[0]);
        System.out.println(format("%d tabelas encontradas", datasource1.getTableNames().size()));

        System.out.print("Iniciando leitura das tabelas do schema " + args[1]);
        Datasource datasource2 = new Datasource(args[1]);
        System.out.println(format("%d tabelas encontradas", datasource2.getTableNames().size()));

        System.out.println("Leitura de datasources finalizada, iniciando diff...");
        System.out.println("=====================================================================================");

        SchemasTableDiff tableDiff = SchemasTableDiff.getInstance();
        Set<String> tablesFromDatasource1ThatIsNotInDatasource2 = tableDiff.from(datasource2)
                .getTablesThatIsNotIn(datasource1);

        System.out.println(
                format("Foram encontradas %d tabelas no schema %s que não estão no schema %s",
                        args[1], args[0] ,tablesFromDatasource1ThatIsNotInDatasource2.size()));
        System.out.println("=====================================================================================");
        tablesFromDatasource1ThatIsNotInDatasource2.forEach(System.out::println);
        System.out.println("=====================================================================================");
    }
}