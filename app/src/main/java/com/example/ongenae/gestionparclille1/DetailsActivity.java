package com.example.ongenae.gestionparclille1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ongenae.gestionparclille1.database.DatabaseHelper;
import com.example.ongenae.gestionparclille1.database.Issue;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper = null;

    private Issue issue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView type = findViewById(R.id.type_details);
        TextView adresse = findViewById(R.id.adresse_details);
        TextView details = findViewById(R.id.details_details);

        Integer issueId = getIntent().getIntExtra("issue", -1);
        try {
            issue = getHelper().getIssueDao().queryForId(issueId);
            type.setText(issue.getmType().toString());
            adresse.setText(issue.getmAdresse());
            details.setText(issue.getmDetails());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Supprime le problème avec une alerte de confirmation
     * @param view, le bouton cliqué
     */
    public void remove_issue_on_click(View view) {
        if (view.getId() == R.id.btn_suppression) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Voulez-vous vraiment supprimer ce problème ?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                getHelper().getIssueDao().delete(issue);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
        }
    }

    /**
     * OnClick ouvrant la google map associée aux coordonnées gps du problème
     * @param view, le bouton ayant lancé la méthode
     */
    public void open_map_onClick(View view) {
        if (view.getId() == R.id.open_map_button) {
            Uri location = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + issue.getmLatitude() + "," + issue.getmLongitude());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

            System.out.println(location.getQuery());

            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

            if (isIntentSafe) {
                startActivity(mapIntent);
            }
        }
    }

    private DatabaseHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDbHelper;
    }

}
