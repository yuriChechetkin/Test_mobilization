package com.mobilization.networkModule;

import com.mobilization.Constants;
import com.mobilization.models.TranslateResponse;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by mac on 03.04.17.
 */

public class ApiImpl {

    private Api api;

    public ApiImpl(Retrofit retrofit) {
        api = retrofit.create(Api.class);
    }

    public Observable<TranslateResponse> getTranslate(String lang, String... text) {
        return api.getTranslate(Constants.API_KEY, lang, text);
    }
}
