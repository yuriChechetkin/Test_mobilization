package com.mobilization.favorites.favorite;

import com.mobilization.models.Translate;

import java.util.List;

/**
 * Created by mac on 04.04.17.
 */

public interface FavoriteInteractor {

    List<Translate> getFavorite();

    List<Translate> getFavorite(String substr);

    void deleteFavorite(Translate t);

    void clearHistory();

    void addFavorite(Translate t);

}