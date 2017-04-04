package com.mobilization.main;

import com.mobilization.storeModule.StoreInteractor;
import com.mobilization.models.Translate;
import com.mobilization.models.TranslateResponse;
import com.mobilization.networkModule.ApiImpl;

import rx.Observable;

/**
 * Created by mac on 02.04.17.
 */

class MainInteractorImpl implements MainInteractor {
    private ApiImpl apiImpl;
    private StoreInteractor storeInteractor;


    MainInteractorImpl(ApiImpl apiImpl, StoreInteractor storeInteractor) {
        this.apiImpl = apiImpl;
        this.storeInteractor = storeInteractor;
    }

    @Override
    public Observable<TranslateResponse> getTranslation(String... str) {

        return apiImpl.getTranslate("en-ru", str);

    }

    @Override
    public String getHistory() {
        String res = "";
        for (Translate t : storeInteractor.getHistoryTranslates()) {
            res += t.getOriginalText() + " t: " + t.getTranslatedText();
        }
        storeInteractor.clearHistory();
        return res;
    }


    @Override
    public void addHistory(Translate t) {
        storeInteractor.addHistoryTranslate(t);
    }

    @Override
    public void setFavorite(Translate t) {
        storeInteractor.setFavorite(t);
    }

    @Override
    public void unFavorite(Translate t) {
        storeInteractor.unFavorite(t);
    }

    @Override
    public boolean isFavorite(Translate t) {
        return storeInteractor.isFavorite(t);
    }
}