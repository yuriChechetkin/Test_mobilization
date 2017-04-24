package com.mobilization.networkModule;

import com.mobilization.models.LanguageResponse;
import com.mobilization.models.LanguagesResponse;
import com.mobilization.models.TranslateResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mac on 03.04.17.
 */

public interface Api {

    @Headers("Content-Type: application/json")
    @GET("translate")
    Observable<TranslateResponse> getTranslate(@Query("key") String key, @Query("lang") String lang, @Query("text") String... text);

    @Headers("Content-Type: application/json")
    @GET("detect")
    Observable<LanguageResponse> detectLanguage(@Query("key") String key, @Query("text") String text);

    @Headers("Content-Type: application/json")
    @GET("getLangs")
    Observable<LanguagesResponse> getLangs(@Query("key") String key, @Query("ui") String ui);

}
