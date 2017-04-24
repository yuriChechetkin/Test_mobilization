package com.mobilization.languages;

import com.mobilization.favorites.favorite.FavoriteView;

/**
 * Created by mac on 23.04.17.
 */

public interface SelectLanguagePresenter {

    void displayLangsByUi(String ui);

    void displayAllLangs();

    void setView(SelectLanguageView view);

    void destroy();

}
