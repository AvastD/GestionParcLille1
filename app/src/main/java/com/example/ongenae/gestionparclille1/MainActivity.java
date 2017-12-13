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
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            Dao<Issue, Integer> tIssueDao = getHelper().getIssueDao();
            mIssues = tIssueDao.queryForAll();
            /* Cette partie sert uniquement à la génération de données de test, elle ne serait pas présente dans la version finale */
            /* DEBUT */
            if (mIssues.isEmpty()) {
                System.out.println("--------------------- création de la db ---------------------");
                genererBDD(tIssueDao);
                mIssues = tIssueDao.queryForAll();
            }
            /* FIN */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RecyclerViewAdapter tRecyclerViewAdapter = new RecyclerViewAdapter(mIssues);
        mRecyclerView.setAdapter(tRecyclerViewAdapter);
        tRecyclerViewAdapter.setClickListener(itemClickListener);

    }

    /**
     * OnClick pour le bouton flottant permettant d'accéder à l'activity d'ajout de problème
     * @param view, le bouton ayant lancé la méthode
     */
    public void go_to_add_issue(View view) {
        Intent intent = new Intent(getApplicationContext(), AddIssueActivity.class);
        startActivity(intent);
    }

    private DatabaseHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDbHelper;
    }

    /* génération d'une base de données fictive pour les tests */
    private void genererBDD(Dao<Issue,Integer> dao) throws SQLException {
        Issue issue = new Issue(TypeEnum.CARVE_HEDGE, 50.6257903,3.1346241, "Cité scientifique", "La haie masque la route.");
        dao.create(issue);
        issue = new Issue(TypeEnum.WEED, 50.609413,3.136318, "M5", "Il y a de la mauvaise herbe devant le batiment.");
        dao.create(issue);
        issue = new Issue(TypeEnum.RUBBISH, 50.608965,3.139054, "M1", "Des poubelles trainent.");
        dao.create(issue);
        issue = new Issue(TypeEnum.TO_CUT_TREE, 50.6044911,3.1437324, "IRCICA", "L'arbre devient envahissant.");
        dao.create(issue);
        issue = new Issue(TypeEnum.OTHER, 50.6042699,3.1360684, "CNRS", "Gars louche qui traine autour de l'entrée.");
        dao.create(issue);
        issue = new Issue(TypeEnum.CARVE_TREE, 50.6057876,3.1458941, "INRIA", "Les branches sont agacantes.");
        dao.create(issue);
        issue = new Issue(TypeEnum.OTHER, 50.608013,3.144034, "SN1", "Colis abandonné, bombe ???");
        dao.create(issue);
        issue = new Issue(TypeEnum.TO_CUT_TREE, 50.6115955,3.1401752, "Métro cité scientifique", "L'arbre est devant une place de parking.");
        dao.create(issue);
        issue = new Issue(TypeEnum.RUBBISH, 50.607806,3.136296, "Polytech'Lille", "Des déchets trainent près du parking.");
        dao.create(issue);
        issue = new Issue(TypeEnum.WEED, 50.610279,3.144289, "C10", "La mauvaise herbe devient envahissante.");
        dao.create(issue);

    }
}
