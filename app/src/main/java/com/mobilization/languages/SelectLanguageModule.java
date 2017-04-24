package com.mobilization.languages;
import com.mobilization.storeModule.StoreInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 23.04.17.
 */

@Module
public class SelectLanguageModule {
    @Provides
    SelectLanguageInteractor provideSelectLanguageInteractor(StoreInteractor storeInteractor) {
        return new SelectLanguageInteractorImpl(storeInteractor);
    }

    @Provides
    SelectLanguagePresenter provideSelectLanguagePresenter(SelectLanguageInteractor interactor) {
        return new SelectLanguagePresenterImpl(interactor);
    }
}