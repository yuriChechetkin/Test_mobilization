package com.mobilization.storeModule;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;

import java.util.List;

/**
 * Created by mac on 03.04.17.
 */

public interface StoreInteractor
{
    void setLastLangs(Language l1, Language l2);
    List<Language> getLastLangs();
    void setLastTranslate(Translate t);
    Translate getLastTranslate();

    void setLastOriginalLangs(Language l1, Language l2, Language l3);
    List<Language> getLastOriginalLangs();
    void setLastTranslateLangs(Language l1, Language l2, Language l3);
    List<Language> getLastTranslateLangs();

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
    void clearFavorite();

    List<Translate> searchHistory(String substr);
    List<Translate> searchFavorite(String substr);

}