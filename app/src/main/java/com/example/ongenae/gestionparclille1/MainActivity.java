package com.example.ongenae.gestionparclille1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * On click pour le bouton flottant permettant d'accéder à l'activity d'ajou de problème
     * @param view
     */
    public void go_to_add_issue(View view) {
        // lancer add_issue_activity
    }
}
