package com.mobilization.favorites.favorite;

import com.mobilization.models.Translate;

import java.util.List;

/**
 * Created by mac on 04.04.17.
 */

public interface FavoriteView {

    void showFavorite(List<Translate> history);

    void deleteFavorite(Translate t);

    void clearHistory();

    void addFavorite(Translate t);

    void loadingStarted();

    void loadingFailed(String errorMessage);

    void onFavoriteClicked(Translate translation);
}