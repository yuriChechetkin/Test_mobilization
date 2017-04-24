package com.mobilization.main;

import com.mobilization.models.Language;
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

        String translate = storeInteractor.getCurrentOriginalLang().getUi() + "-" + storeInteractor.getCurrentTranslateLang().getUi();

        return apiImpl.getTranslate(translate, str);

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
    public Language getCurrentOriginalLang() {
        return storeInteractor.getCurrentOriginalLang();
    }

    @Override
    public Language getCurrentTranslateLang() {
        return storeInteractor.getCurrentTranslateLang();
    }

    @Override
    public void swapLang() {
        Language originalLang = storeInteractor.getCurrentOriginalLang();
        Language translateLang = storeInteractor.getCurrentTranslateLang();

        storeInteractor.setCurrentTranslateLang(originalLang);
        storeInteractor.setCurrentOriginalLang(translateLang);
    }

    @Override
    public void setOriginalLang(Language l) {
        storeInteractor.setCurrentOriginalLang(l);
    }

    @Override
    public void setTranslateLang(Language l) {
        storeInteractor.setCurrentTranslateLang(l);
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

    @Override
    public Translate getLastTranslate() {
        return storeInteractor.getLastTranslate();
    }

    @Override
    public void setLastTranslate(Translate t) {
        storeInteractor.setLastTranslate(t);
    }

    @Override
    public Language getLangByUi(String ui) {
        return storeInteractor.getLangByUi(ui);
    }
}