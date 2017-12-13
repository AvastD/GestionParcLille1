package com.example.ongenae.gestionparclille1.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ongenae.gestionparclille1.R;
import com.example.ongenae.gestionparclille1.TypeEnum;
import com.example.ongenae.gestionparclille1.database.Issue;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    private TextView probleme, lieu;
    private ImageView image;
    private RecyclerViewClickListener clickListener;

    //itemView est la vue correspondante à une cellule
    RecyclerViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);

        probleme = itemView.findViewById(R.id.probleme);
        lieu = itemView.findViewById(R.id.lieu);
        image = itemView.findViewById(R.id.img_problem);

        itemView.setOnClickListener(this);
        this.clickListener = clickListener;
    }

    //Fonction pour remplir la cellule en fonction d'un Tweet
    public void bind(final Issue issue){
        TypeEnum type = issue.getmType();
        probleme.setText(type.toString());
        lieu.setText(issue.getmAdresse());

        switch(type) {
            case CARVE_TREE:
                image.setImageResource(R.drawable.branche_arbre_r);
                break;
            case TO_CUT_TREE:
                image.setImageResource(R.drawable.arbre_r);
                break;
            case RUBBISH:
                image.setImageResource(R.drawable.dechets_r);
                break;
            case CARVE_HEDGE:
                image.setImageResource(R.drawable.haie_a_tailler_r);
                break;
            case WEED:
                image.setImageResource(R.drawable.mauvaise_herbe_r);
                break;
            case OTHER:
                image.setImageResource(R.drawable.question_mark);
        }
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getAdapterPosition());
    }

}