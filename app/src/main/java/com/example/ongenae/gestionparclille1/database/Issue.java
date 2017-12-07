package com.example.ongenae.gestionparclille1.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Avast on 07/12/2017.
 */

@DatabaseTable(tableName="issues")
public class Issue implements Serializable {

    public static final String ISSUE_ID = "id";
    public static final String ISSUE_TYPE = "type";
    public static final String ISSUE_DATE = "date";
    // ...

    public Issue() {

    }

    @DatabaseField(columnName = ISSUE_ID, generatedId = true)
    private Integer mid;

    @DatabaseField(columnName = ISSUE_TYPE)
    private String type;

    // ...
}
