package com.mobilization.storeModule;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mac on 03.04.17.
 */

public interface StoreInteractor
{
    //current languages
    void setCurrentOriginalLang(Language l);
    Language getCurrentOriginalLang();
    void setCurrentTranslateLang(Language l);
    Language getCurrentTranslateLang();

    void setLastTranslate(Translate t);
    Translate getLastTranslate();

    //dirs & langs
    void setDirs(ArrayList<String> dirs);
    void setLangs(Map<String, String> langs);
    ArrayList<Language> getDirsByUi(String ui);
    ArrayList<Language> getLangs();
    Language getLangByUi(String ui);

    void addHistoryTranslate(Translate t);
    void deleteHistoryTranslate(Translate t);
    Translate getHistoryTranslateById(int id);
    List<Translate> getHistoryTranslates();

    void setFavorite(Translate t);
    boolean isFavorite(Translate t);
    void unFavorite(Translate t);
    Translate getFavoriteTranslateById(int id);
    List<Translate> getFavoriteTranslates();

    void clearHistory();

    List<Translate> searchHistory(String substr);
    List<Translate> searchFavorite(String substr);

}