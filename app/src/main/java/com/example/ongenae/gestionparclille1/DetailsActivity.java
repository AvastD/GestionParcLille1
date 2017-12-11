package com.example.ongenae.gestionparclille1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ongenae.gestionparclille1.database.DatabaseHelper;
import com.example.ongenae.gestionparclille1.database.Issue;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

/**
 * Created by ongenae on 11/12/17.
 */

public class DetailsActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView title = findViewById(R.id.titre_details);
        Integer issueId = getIntent().getIntExtra("issue", -1);
        try {
            Issue issue = getHelper().getIssueDao().queryForId(issueId);
            title.setText(issue.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void remove_issue_on_click(View view) {
        //TODO supprimer l'issue de la db
    }

    private DatabaseHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDbHelper;
    }
}
