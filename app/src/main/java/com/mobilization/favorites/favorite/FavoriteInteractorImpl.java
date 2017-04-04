package com.mobilization.favorites.favorite;

import com.mobilization.models.Translate;
import com.mobilization.storeModule.StoreInteractor;

import java.util.List;

/**
 * Created by mac on 04.04.17.
 */

public class FavoriteInteractorImpl implements FavoriteInteractor {

    private StoreInteractor storeInteractor;


    FavoriteInteractorImpl(StoreInteractor storeInteractor) {
        this.storeInteractor = storeInteractor;
    }

    @Override
    public List<Translate> getFavorite() {
        return storeInteractor.getFavoriteTranslates();
    }

    @Override
    public List<Translate> getFavorite(String substr) {
        return storeInteractor.searchFavorite(substr);
    }

    @Override
    public void deleteFavorite(Translate t) {

    }

    @Override
    public void addFavorite(Translate t) {

    }
}
