package com.mobilization.favorites.favorite;

import com.mobilization.favorites.history.HistoryView;
import com.mobilization.models.Translate;

/**
 * Created by mac on 04.04.17.
 */

public interface FavoritePresenter {

    void deleteFavorite(Translate t);

    void clearHistory();

    void addFavorite(Translate t);

    void displayFavorite();

    void displayFavorite(String substr);

    void setView(FavoriteView view);

    void destroy();

}
