package com.mobilization.favorites.favorite;

import com.mobilization.storeModule.StoreInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 04.04.17.
 */

@Module
public class FavoriteModule {
    @Provides
    FavoriteInteractor provideFavoriteInteractor(StoreInteractor storeInteractor) {
        return new FavoriteInteractorImpl(storeInteractor);
    }

    @Provides
    FavoritePresenter provideFavoritePresenter(FavoriteInteractor interactor) {
        return new FavoritePresenterImpl(interactor);
    }
}