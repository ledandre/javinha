package com.github.ledandre.files.textsearch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSeeker {

    private static final Pattern TABLE_NAME_REGEX = Pattern.compile("(FROM|from|join|JOIN)\\s(\\w+)");
    private static final Pattern TABLE_NAME_REGEX_ENTITY = Pattern.compile("(@Table).+(?!=|\\s)\"(\\w+)");
    private static final int TABLE_NAME_GROUP = 2;

    public boolean seek(String tableName, String str) {
        boolean usedInQuery = findUsage(tableName, TABLE_NAME_REGEX.matcher(str));
        boolean usedInEntity = findUsage(tableName, TABLE_NAME_REGEX_ENTITY.matcher(str));
        return (usedInQuery || usedInEntity);
    }

    private boolean findUsage (final String tableName, final Matcher matcher) {
        if (!matcher.find()) {
            return false;
        }

        String extractedTableName = matcher.group(TABLE_NAME_GROUP);
        return extractedTableName.toLowerCase().equals(tableName.toLowerCase());
    }
}
