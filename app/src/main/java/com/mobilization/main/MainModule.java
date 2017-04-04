package com.mobilization.main;

import com.mobilization.storeModule.StoreInteractor;
import com.mobilization.networkModule.ApiImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 02.04.17.
 */

@Module
public class MainModule {
    @Provides
    MainInteractor provideMainInteractor(ApiImpl apiImpl, StoreInteractor storeInteractor) {
        return new MainInteractorImpl(apiImpl, storeInteractor);
    }

    @Provides
    MainPresenter provideMainPresenter(MainInteractor interactor) {
        return new MainPresenterImpl(interactor);
    }
}