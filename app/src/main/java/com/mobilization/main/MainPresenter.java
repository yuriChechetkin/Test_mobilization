package com.mobilization.main;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;

/**
 * Created by mac on 02.04.17.
 */

public interface MainPresenter {
    void displayTranslation(String... str);

    void addFavorite(Translate translate);

    void setView(MainView view);

    void destroy();

    void selectOriginalLang();

    void selectTranslateLang();

    void swapLang();

    void setOriginalLang(Language lang);

    void setTranslateLang(Language lang);
}