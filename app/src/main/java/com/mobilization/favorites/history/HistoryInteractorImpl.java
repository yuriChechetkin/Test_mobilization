package com.mobilization.favorites.history;

import com.mobilization.models.Translate;
import com.mobilization.networkModule.ApiImpl;
import com.mobilization.storeModule.StoreInteractor;

import java.util.List;

/**
 * Created by mac on 04.04.17.
 */

public class HistoryInteractorImpl implements HistoryInteractor {


    private StoreInteractor storeInteractor;


    HistoryInteractorImpl(StoreInteractor storeInteractor) {
        this.storeInteractor = storeInteractor;
    }


    @Override
    public void addHistory(Translate t) {

    }

    @Override
    public List<Translate> getHistory() {
        return storeInteractor.getHistoryTranslates();
    }

    @Override
    public List<Translate> getHistory(String substr) {
        return storeInteractor.searchHistory(substr);
    }

    @Override
    public void deleteHistory(Translate t) {

    }

    @Override
    public void addFavorite(Translate t) {

    }
}
