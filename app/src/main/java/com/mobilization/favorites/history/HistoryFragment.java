package com.mobilization.favorites.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxSearchView;
import com.mobilization.BaseApplication;
import com.mobilization.R;
import com.mobilization.models.Translate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac on 04.04.17.
 */

public class HistoryFragment extends Fragment implements HistoryView {

    @Inject
    HistoryPresenter historyPresenter;

    @Inject
    Context c;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.searchView)
    SearchView searchView;

    private RecyclerView.Adapter adapter;
    private List<Translate> translates = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getActivity().getApplication()).createHistoryComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyPresenter.setView(this);
        initLayoutReferences();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rv.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter(translates, this);
        rv.setAdapter(adapter);
    }

    private void initLayoutReferences() {
        initRecyclerView();
        RxSearchView.queryTextChanges(searchView)
                //.debounce(500, TimeUnit.MILLISECONDS)
                //.filter(charSequence -> charSequence.length()>0)
                .subscribe(this::onCompleteQuery);
    }

    private void onCompleteQuery(CharSequence charSequence) {
        historyPresenter.displayHistory(charSequence.toString());
    }


    @Override
    public void showHistory(List<Translate> historyTranslates) {
        this.translates.clear();
        this.translates.addAll(historyTranslates);
        rv.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteHistory(Translate t) {

    }

    @Override
    public void addFavorite(Translate t) {

    }

    @Override
    public void loadingStarted() {

    }

    @Override
    public void loadingFailed(String errorMessage) {

    }

    public void displayData() {
        historyPresenter.displayHistory();
    }

    @Override
    public void onHistoryClicked(Translate translation) {
        Toast.makeText(getContext(), translation.getOriginalText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        historyPresenter.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseHistoryComponent();
    }
}
