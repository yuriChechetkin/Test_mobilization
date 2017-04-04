package com.mobilization.favorites.history;

import com.mobilization.models.Translate;

/**
 * Created by mac on 04.04.17.
 */

public class HistoryPresenterImpl implements HistoryPresenter {


    private HistoryView view;
    private HistoryInteractor historyInteractor;


    HistoryPresenterImpl(HistoryInteractor interactor) {
        historyInteractor = interactor;
    }

    @Override
    public void setView(HistoryView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
    }

    @Override
    public void displayHistory() {
        view.showHistory(historyInteractor.getHistory());
    }

    @Override
    public void displayHistory(String substr) {
        view.showHistory(historyInteractor.getHistory(substr));
    }

    @Override
    public void addFavorite(Translate t) {

    }

    @Override
    public void deleteHistory(Translate t) {

    }
}
