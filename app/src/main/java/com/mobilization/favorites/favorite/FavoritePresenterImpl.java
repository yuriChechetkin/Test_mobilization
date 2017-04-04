package com.mobilization.favorites.favorite;

import com.mobilization.models.Translate;


/**
 * Created by mac on 04.04.17.
 */

public class FavoritePresenterImpl implements FavoritePresenter {

    private FavoriteView view;
    private FavoriteInteractor favoriteInteractor;


    FavoritePresenterImpl(FavoriteInteractor interactor) {
        favoriteInteractor = interactor;
    }


    @Override
    public void displayFavorite() {
        view.showFavorite(favoriteInteractor.getFavorite());
    }

    @Override
    public void displayFavorite(String substr) {
        view.showFavorite(favoriteInteractor.getFavorite(substr));
    }

    @Override
    public void setView(FavoriteView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
    }

    @Override
    public void deleteFavorite(Translate t) {

    }

    @Override
    public void addFavorite(Translate t) {

    }
}
