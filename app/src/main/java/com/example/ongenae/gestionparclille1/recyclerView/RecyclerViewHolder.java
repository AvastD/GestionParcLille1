package com.example.ongenae.gestionparclille1.recyclerView;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ongenae.gestionparclille1.R;
import com.example.ongenae.gestionparclille1.database.Issue;

/**
 * Created by ongenae on 11/12/17.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    private TextView probleme, lieu;
    private ImageView image;
    private RecyclerViewClickListener clickListener;

    //itemView est la vue correspondante à une cellule
    public RecyclerViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);

        probleme = (TextView) itemView.findViewById(R.id.probleme);
        lieu = (TextView) itemView.findViewById(R.id.lieu);
        image = (ImageView) itemView.findViewById(R.id.img_problem);

        itemView.setOnClickListener(this);
        this.clickListener = clickListener;
    }

    //Fonction pour remplir la cellule en fonction d'un Tweet
    public void bind(final Issue issue){
        probleme.setText(issue.getmType());
        lieu.setText(issue.getmAdresse());

        // TODO ajouter l'image selon le type du problème
        image.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getAdapterPosition());
    }

}