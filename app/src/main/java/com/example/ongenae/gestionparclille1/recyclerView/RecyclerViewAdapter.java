package com.example.ongenae.gestionparclille1.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ongenae.gestionparclille1.R;
import com.example.ongenae.gestionparclille1.database.Issue;

import java.util.List;

/**
 * Created by ongenae on 11/12/17.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Issue> list;
    private RecyclerViewHolder.RecyclerViewClickListener clickListener;

    public RecyclerViewAdapter(List<Issue> list) {
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_problem,viewGroup,false);

        return new RecyclerViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder myViewHolder, int position) {
        Issue issue = list.get(position);
        myViewHolder.bind(issue);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(RecyclerViewHolder.RecyclerViewClickListener clickListener){
        this.clickListener = clickListener;
    }

}
