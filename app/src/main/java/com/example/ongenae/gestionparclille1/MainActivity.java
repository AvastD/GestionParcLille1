package com.example.ongenae.gestionparclille1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ongenae.gestionparclille1.database.DatabaseHelper;
import com.example.ongenae.gestionparclille1.database.Issue;
import com.example.ongenae.gestionparclille1.recyclerView.RecyclerViewAdapter;
import com.example.ongenae.gestionparclille1.recyclerView.RecyclerViewHolder;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<Issue> mIssues;
    private DatabaseHelper mDbHelper = null;
    private RecyclerViewHolder.RecyclerViewClickListener itemClickListener = new RecyclerViewHolder.RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra("issue", mIssues.get(position).getmId());
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        try {
            Dao<Issue, Integer> tIssueDao = getHelper().getIssueDao();
            mIssues = tIssueDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mRecyclerViewAdapter = new RecyclerViewAdapter(mIssues);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setClickListener(itemClickListener);
    }

    /**
     * On click pour le bouton flottant permettant d'accéder à l'activity d'ajou de problème
     * @param view, la vue associée
     */
    public void go_to_add_issue(View view) {
        // lancer add_issue_activity
    }

    private DatabaseHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDbHelper;
    }
}
