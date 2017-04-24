package com.mobilization.splash;

import com.mobilization.models.Language;
import com.mobilization.models.LanguagesResponse;
import com.mobilization.models.TranslateResponse;
import com.mobilization.networkModule.ApiImpl;
import com.mobilization.storeModule.StoreInteractor;

import java.util.ArrayList;
import java.util.Map;

import rx.Observable;

/**
 * Created by mac on 11.04.17.
 */

public class SplashInteractorImpl implements SplashInteractor {

    private ApiImpl apiImpl;
    private StoreInteractor storeInteractor;


    SplashInteractorImpl(ApiImpl apiImpl, StoreInteractor storeInteractor) {
        this.apiImpl = apiImpl;
        this.storeInteractor = storeInteractor;
    }

    @Override
    public Observable<LanguagesResponse> getLangs() {
        return apiImpl.getLangs("ru");
    }

    @Override
    public void saveLangs(LanguagesResponse lr) {
        storeInteractor.setLangs(lr.getLangs());
        storeInteractor.setDirs(lr.getDirs());
    }

    @Override
    public ArrayList<Language> getDirsByUi(String fromUi) {
        return storeInteractor.getDirsByUi(fromUi);
    }
}
