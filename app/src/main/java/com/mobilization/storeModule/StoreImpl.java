package com.mobilization.storeModule;

import android.content.Context;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mac on 03.04.17.
 */

@Singleton
public class StoreImpl {

    @Inject
    public StoreImpl(Context context) {
        if (!hawkIsBuild()) {
            Hawk.init(context).build();
        }
    }

    public boolean hawkIsBuild() {
        return Hawk.isBuilt();
    }


    public void setFavorite(Translate translate) {
        List<Translate> historyTranslates = getHistoryTranslates();
        for (int i = 0; i < historyTranslates.size(); i++) {
            if (translate.equals(historyTranslates.get(i))) {
                translate.setFavorite(true);
                historyTranslates.set(i, translate);
            }
        }
        setHistoryTranslates(historyTranslates);
    }

    public boolean isFavorite(Translate t) {

        List<Translate> translates = getFavoriteTranslates();
        for (Translate trans : translates) {
            if (trans.equals(t))
                return true;
        }
        return false;
    }

    public void unFavorite(Translate translate) {
        List<Translate> historyTranslates = getHistoryTranslates();
        for (int i = 0; i < historyTranslates.size(); i++) {
            if (translate.equals(historyTranslates.get(i))) {
                translate.setFavorite(false);
                historyTranslates.set(i, translate);
            }
        }
        setHistoryTranslates(historyTranslates);
    }

    public List<Translate> getFavoriteTranslates() {
        List<Translate> favoriteTranslates = new ArrayList<>();
        List<Translate> historyTranslates = getHistoryTranslates();
        for (int i = 0; i < historyTranslates.size(); i++) {
            if (historyTranslates.get(i).isFavorite()) {
                favoriteTranslates.add(historyTranslates.get(i));
            }
        }
        return favoriteTranslates;
    }

    public Translate getFavoriteTranslateById(int id) {
        return null;
    }


    public void setFavoriteTranslates(List<Translate> translates) {
        Hawk.put("favoriteTranslates", translates);
    }


    public void setLastLangs(Language l1, Language l2) {
        Hawk.put("lastLang1", l1);
        Hawk.put("lastLang2", l2);
    }


    public List<Language> getLastLangs() {
        List<Language> l = new ArrayList<>();
        l.add(Hawk.get("lastLang1"), new Language());
        l.add(Hawk.get("lastLang2"), new Language());
        return l;
    }


    public void setLastTranslate(Translate t) {
        Hawk.put("lastTranslate", t);
    }


    public Translate getLastTranslate() {
        return Hawk.get("lastTranslate", new Translate());
    }


    public void setLastOriginalLangs(Language l1, Language l2, Language l3) {
        Hawk.put("lastOriginalLang1", l1);
        Hawk.put("lastOriginalLang2", l2);
        Hawk.put("lastOriginalLang3", l3);
    }


    public List<Language> getLastOriginalLangs() {
        List<Language> l = new ArrayList<>();
        l.add(Hawk.get("lastOriginalLang1"), new Language());
        l.add(Hawk.get("lastOriginalLang2"), new Language());
        l.add(Hawk.get("lastOriginalLang3"), new Language());
        return l;
    }


    public void setLastTranslateLangs(Language l1, Language l2, Language l3) {
        Hawk.put("lastTranslateLang1", l1);
        Hawk.put("lastTranslateLang2", l2);
        Hawk.put("lastTranslateLang3", l3);
    }


    public List<Language> getLastTranslateLangs() {
        List<Language> l = new ArrayList<>();
        l.add(Hawk.get("lastTranslateLang1"), new Language());
        l.add(Hawk.get("lastTranslateLang2"), new Language());
        l.add(Hawk.get("lastTranslateLang3"), new Language());
        return l;
    }


    public void addHistoryTranslate(Translate t) {
        List<Translate> translates = getHistoryTranslates();
        translates.add(t);
        setHistoryTranslates(translates);
    }

    public void setHistoryTranslates(List<Translate> translates) {
        Hawk.put("historyTranslates", translates);
    }


    public Translate getHistoryTranslateById(int id) {
        List<Translate> translates = getHistoryTranslates();
        for (Translate t : translates) {
            if (t.hashCode() == id)
                return t;
        }
        return new Translate();
    }


    public List<Translate> getHistoryTranslates() {
        return Hawk.get("historyTranslates", new ArrayList<Translate>());
    }

    public void deleteHistoryTranslate(Translate t) {
        List<Translate> translates = getHistoryTranslates();
        for (Translate trans : translates) {
            if (trans.equals(t))
                translates.remove(t);
        }
        setHistoryTranslates(translates);
    }


    public void clearHistory() {
        Hawk.delete("historyTranslates");
    }


    public void clearFavorite() {
        Hawk.delete("favoriteTranslates");
    }


    public List<Translate> searchHistory(String substr) {
        List<Translate> queryTranslates = new ArrayList<>();
        List<Translate> translates = getHistoryTranslates();
        for (Translate t : translates) {
            if (t.getTranslatedText().contains(substr) || t.getOriginalText().contains(substr)) {
                queryTranslates.add(t);
            }
        }

        return queryTranslates;
    }


    public List<Translate> searchFavorite(String substr) {
        List<Translate> queryTranslates = new ArrayList<>();
        List<Translate> translates = getFavoriteTranslates();
        for (Translate t : translates) {
            if (t.getTranslatedText().contains(substr) || t.getOriginalText().contains(substr)) {
                queryTranslates.add(t);
            }
        }

        return queryTranslates;
    }
}