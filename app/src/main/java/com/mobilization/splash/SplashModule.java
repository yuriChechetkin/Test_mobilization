package com.mobilization.splash;

import dagger.Module;

import com.mobilization.networkModule.ApiImpl;
import com.mobilization.storeModule.StoreInteractor;
import dagger.Provides;

/**
 * Created by mac on 11.04.17.
 */

@Module
public class SplashModule {
    @Provides
    SplashInteractor provideSplashInteractor(ApiImpl apiImpl, StoreInteractor storeInteractor) {
        return new SplashInteractorImpl(apiImpl, storeInteractor);
    }

    @Provides
    SplashPresenter provideSplashPresenter(SplashInteractor interactor) {
        return new SplashPresenterImpl(interactor);
    }
}