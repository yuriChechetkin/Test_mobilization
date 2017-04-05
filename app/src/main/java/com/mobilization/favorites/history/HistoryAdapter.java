package com.mobilization.favorites.history;

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

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    private List<Translate> translates;
    private Context context;
    private HistoryView view;

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
        public int position=0;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            HistoryAdapter.this.view.onHistoryClicked(translate);
        }
    }

    public HistoryAdapter(List<Translate> translates, HistoryView historyView) {
        this.translates = translates;
        view = historyView;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.translate = translates.get(position);
        holder.position = position;
        if (holder.translate.isFavorite())
            holder.btnFavorite.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary));
        else
            holder.btnFavorite.setColorFilter(ContextCompat.getColor(context, R.color.colorDarkGray));

        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(translates.get(position).isFavorite()){
                    translates.get(position).setFavorite(false);
                }else{
                    translates.get(position).setFavorite(true);
                }
                HistoryAdapter.this.view.addFavorite(holder.translate);
                HistoryAdapter.this.notifyItemChanged(position);
            }
        });

        holder.tvTranslatedText.setText(holder.translate.getTranslatedText());
        holder.tvOriginalText.setText(holder.translate.getOriginalText());
    }

    @Override
    public int getItemCount() {
        return translates.size();
    }
}
