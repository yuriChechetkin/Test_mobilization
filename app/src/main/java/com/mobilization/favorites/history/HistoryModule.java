package com.mobilization.favorites.history;

import com.mobilization.storeModule.StoreInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 04.04.17.
 */

@Module
public class HistoryModule {
    @Provides
    HistoryInteractor provideHistoryInteractor(StoreInteractor storeInteractor) {
        return new HistoryInteractorImpl(storeInteractor);
    }

    @Provides
    HistoryPresenter provideHistoryPresenter(HistoryInteractor interactor) {
        return new HistoryPresenterImpl(interactor);
    }
}