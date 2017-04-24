package com.mobilization.storeModule;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mac on 03.04.17.
 */

public class StoreInteractorImpl implements StoreInteractor {

    private StoreImpl storeImpl;

    StoreInteractorImpl(StoreImpl store) {
        storeImpl = store;
    }


    @Override
    public void setCurrentOriginalLang(Language l) {
        storeImpl.setCurrentOriginalLang(l);
    }

    @Override
    public Language getCurrentOriginalLang() {
        return storeImpl.getCurrentOriginalLang();
    }

    @Override
    public ArrayList<Language> getLangs() {
        return storeImpl.getLangs();
    }

    @Override
    public void setCurrentTranslateLang(Language l) {
        storeImpl.setCurrentTranslateLang(l);
    }

    @Override
    public Language getCurrentTranslateLang() {
        return storeImpl.getCurrentTranslateLang();
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
    public Language getLangByUi(String ui) {
        return storeImpl.getLangByUi(ui);
    }

    @Override
    public void setDirs(ArrayList<String> dirs) {
        storeImpl.setDirs(dirs);
    }

    @Override
    public void setLangs(Map<String, String> langs) {
        storeImpl.setLangs(langs);
    }

    @Override
    public ArrayList<Language> getDirsByUi(String ui) {
        return storeImpl.getDirsByUi(ui);
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
    public List<Translate> searchHistory(String substr) {
        return storeImpl.searchHistory(substr);
    }

    @Override
    public List<Translate> searchFavorite(String substr) {
        return storeImpl.searchFavorite(substr);
    }
}
