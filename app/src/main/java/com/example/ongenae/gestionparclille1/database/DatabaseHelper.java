package com.example.ongenae.gestionparclille1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Avast on 07/12/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "base.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Issue, Integer> mIssueDao;

    public DatabaseHelper(Context sContext) {
        super(sContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sDatabase, ConnectionSource sConnectionSource) {
        try {
            TableUtils.createTable(sConnectionSource, Issue.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sDatabase, ConnectionSource sConnectionSource, int sOldVersion, int sNewVersion) {
        try {
            TableUtils.dropTable(sConnectionSource, Issue.class, true);
            onCreate(sDatabase, sConnectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + sOldVersion + " to new "
                    + sNewVersion, e);
        }
    }

    /**
     * return dao for issues table
     * @return
     * @throws SQLException
     */
    public Dao<Issue, Integer> getIssueDao() throws SQLException {
        if (mIssueDao == null) {
            mIssueDao = getDao(Issue.class);
        }
        return mIssueDao;
    }
}
