# Javinha

Projeto para automatizar tarefas trabalhosas.

## Funcionalidades

#### SchemaTablesDiff
Utilizado para fazer um _diff_ entre as tabelas de dois _schemas_ diferentes, descobrindo assim quais tabelas estão em apenas um dos _schemas_.

> Em **src/main/resources** devem estar dois arquivos com a lista dos nomes das tabelas de cada schema.

```java
java -classpath /home/ledandre/workspace/javinha/target/classes com.github.ledandre.files.schemadiff.Main "schema1_tables.txt" "schema2_tables.txt"
```
Exemplo de _output_:
```java
=====================================================================================
Iniciando leitura das tabelas do schema SCHEMA1... 1 tabelas encontradas
Iniciando leitura das tabelas do schema SCHEMA2... 2 tabelas encontradas
Leitura de datasources finalizada, iniciando diff...
=====================================================================================
Foram encontradas 1 tabelas no schema SCHEMA1 que não estão no schema SCHEMA2
=====================================================================================
TABLE_1
TABLE_A
TABLE_B
...
```

<hr/>

#### TextSearch
Utilizado para buscar ocorrências do uso de tabelas em queries dentro de arquivos de um repositório e .

> em **src/main/resources** deve haver um arquivo **tables_to_search.txt** com a lista dos nomes das tabelas a serem procuradas.


```java
java -classpath /home/ledandre/workspace/javinha/target/classes com.github.ledandre.files.textsearch.Main "/home/ledandre/workspace/myrepo" ".java,.txt,.sql" "ISO-8859-1"
``` 

Exemplo de output:
```java
=====================================================================================
Iniciando leitura dos arquivos no diretório /home/ledandre/workspace/myrepo com as extensões .java,.txt,.sql
=====================================================================================
Arquivos filtrados com sucesso!
=====================================================================================
Iniciando busca por uso de tabelas...
Buscando a tabela TABLE_1
Buscando a tabela TABLE_A
Buscando a tabela TABLE_B
...
Processo terminado com sucesso! Foram encontradas 2 ocorrências de uso das tabelas informadas
=====================================================================================
A tabela TABLE_1 foi encontrada no arquivo /home/ledandre/workspace/myrepo/DAOOracle.java
A tabela TABLE_B foi encontrada no arquivo /home/ledandre/workspace/myrepo/MyDAO.java
=====================================================================================

```