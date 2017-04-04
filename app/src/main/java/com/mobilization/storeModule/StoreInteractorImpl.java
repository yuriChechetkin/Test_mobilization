package com.mobilization.storeModule;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;

import java.util.List;

/**
 * Created by mac on 03.04.17.
 */

public class StoreInteractorImpl implements StoreInteractor {

    private StoreImpl storeImpl;

    StoreInteractorImpl(StoreImpl store) {
        storeImpl = store;
    }


    @Override
    public void setLastLangs(Language l1, Language l2) {
        storeImpl.setLastLangs(l1, l2);
    }

    @Override
    public List<Language> getLastLangs() {
        return storeImpl.getLastLangs();
    }

    @Override
    public void setLastTranslate(Translate t) {
        storeImpl.setLastTranslate(t);
    }

    @Override
    public Translate getLastTranslate() {
        return storeImpl.getLastTranslate();
    }

    @Override
    public void setLastOriginalLangs(Language l1, Language l2, Language l3) {
        storeImpl.setLastOriginalLangs(l1, l2, l3);
    }

    @Override
    public List<Language> getLastOriginalLangs() {
        return storeImpl.getLastOriginalLangs();
    }

    @Override
    public void setLastTranslateLangs(Language l1, Language l2, Language l3) {
        storeImpl.setLastTranslateLangs(l1, l2, l3);
    }

    @Override
    public List<Language> getLastTranslateLangs() {
        return storeImpl.getLastTranslateLangs();
    }

    @Override
    public void addHistoryTranslate(Translate t) {
        storeImpl.addHistoryTranslate(t);
    }

    @Override
    public void deleteHistoryTranslate(Translate t) {
        storeImpl.deleteHistoryTranslate(t);
    }

    @Override
    public Translate getHistoryTranslateById(int id) {
        return storeImpl.getHistoryTranslateById(id);
    }

    @Override
    public List<Translate> getHistoryTranslates() {
        return storeImpl.getHistoryTranslates();
    }

    @Override
    public void setFavorite(Translate t) {
        storeImpl.setFavorite(t);
    }

    @Override
    public boolean isFavorite(Translate t) {
        return storeImpl.isFavorite(t);
    }

    @Override
    public void unFavorite(Translate t) {
        storeImpl.unFavorite(t);
    }

    @Override
    public Translate getFavoriteTranslateById(int id) {
        return storeImpl.getFavoriteTranslateById(id);
    }

    @Override
    public List<Translate> getFavoriteTranslates() {
        return storeImpl.getFavoriteTranslates();
    }


    @Override
    public void clearHistory() {
        storeImpl.clearHistory();
    }

    @Override
    public void clearFavorite() {
        storeImpl.clearFavorite();
    }

    @Override
    public List<Translate> searchHistory(String substr) {
        return storeImpl.searchHistory(substr);
    }

    @Override
    public List<Translate> searchFavorite(String substr) {
        return storeImpl.searchFavorite(substr);
    }
}
