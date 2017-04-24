package com.mobilization.languages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;
import android.widget.Toast;

import com.mobilization.BaseApplication;
import com.mobilization.MainActivity;
import com.mobilization.R;
import com.mobilization.favorites.favorite.FavoriteAdapter;
import com.mobilization.models.Language;
import com.mobilization.splash.SplashActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac on 23.04.17.
 */

public class SelectLanguageActivity extends AppCompatActivity implements SelectLanguageView{

    @Inject
    SelectLanguagePresenter slPresenter;

    @Inject
    Context c;

    @Inject
    Resources res;

    @BindView(R.id.rvLanguages)
    RecyclerView rvLanguages;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private LanguageAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseApplication)getApplication()).createSlComponent().inject(this);
        slPresenter.setView(this);
        if(getIntent().hasExtra("lang"))
            slPresenter.displayLangsByUi(getIntent().getStringExtra("lang"));
        else
            slPresenter.displayAllLangs();
    }

    @Override
    public void showOriginalLangs(ArrayList<Language> languages, Language currentOriginalLang) {
        initRecyclerView(languages, currentOriginalLang);
    }

    @Override
    public void showTranslateLangs(ArrayList<Language> languages, Language currentTranslateLang) {
        initRecyclerView(languages, currentTranslateLang);
    }

    private void initRecyclerView(ArrayList<Language> languages, Language currentLang) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        rvLanguages.setLayoutManager(layoutManager);
        adapter = new LanguageAdapter(languages, currentLang, this);
        rvLanguages.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvLanguages.getContext(),
                layoutManager.getOrientation());
        dividerItemDecoration.setDrawable(res.getDrawable(R.drawable.item_divider));
        rvLanguages.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onLangClicked(Language language) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("newLang", language);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    protected void onDestroy() {
        slPresenter.destroy();
        super.onDestroy();
    }
}
