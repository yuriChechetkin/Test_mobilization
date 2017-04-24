package com.mobilization.languages;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mobilization.R;
import com.mobilization.models.Language;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac on 23.04.17.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {


    private List<Language> languages;
    private Context context;
    private SelectLanguageView view;
    private Language currentLang;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvLang)
        TextView tvLang;
        @BindView(R.id.ivSelection)
        ImageView ivSelection;
        @BindView(R.id.rlMain)
        RelativeLayout rlMain;

        public Language language;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            currentLang=language;
            notifyDataSetChanged();
            LanguageAdapter.this.view.onLangClicked(language);
        }
    }

    public LanguageAdapter(List<Language> languages, Language currentLang, SelectLanguageView slView) {
        this.languages = languages;
        this.view = slView;
        this.currentLang = currentLang;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_language, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.language = languages.get(position);
        holder.tvLang.setText(holder.language.getName());
        if(holder.language.equals(currentLang)) {
            holder.ivSelection.setVisibility(View.VISIBLE);
            holder.rlMain.setBackgroundColor(context.getResources().getColor(R.color.colorPaleGray));
        }else {
            holder.ivSelection.setVisibility(View.INVISIBLE);
            holder.rlMain.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }
}
