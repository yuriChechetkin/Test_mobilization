package com.mobilization.storeModule;

import com.mobilization.AppModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 03.04.17.
 */

@Module(includes = AppModule.class)
public class StoreModule {
    @Provides
    @Singleton
    StoreInteractor provideStoreInteractor(StoreImpl store) {
        return new StoreInteractorImpl(store);
    }
}
