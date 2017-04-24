package com.mobilization.favorites.favorite;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobilization.R;
import com.mobilization.models.Translate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac on 04.04.17.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {


    private List<Translate> translates;
    private Context context;
    private FavoriteView view;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.btnFavorite)
        ImageButton btnFavorite;
        @BindView(R.id.tvOriginalText)
        TextView tvOriginalText;
        @BindView(R.id.tvTranslatedText)
        TextView tvTranslatedText;
        @BindView(R.id.tvLangs)
        TextView tvLangs;


        public Translate translate;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            FavoriteAdapter.this.view.onFavoriteClicked(translate);
        }
    }

    public FavoriteAdapter(List<Translate> translates, FavoriteView historyView) {
        this.translates = translates;
        view = historyView;
    }

    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.translate = translates.get(position);
        holder.btnFavorite.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary));
        holder.tvTranslatedText.setText(holder.translate.getTranslatedText());
        holder.tvOriginalText.setText(holder.translate.getOriginalText());
        holder.tvLangs.setText(holder.translate.getDirs().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return translates.size();
    }
}
