package com.mobilization.networkModule;

import com.mobilization.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac on 02.04.17.
 */

@Module
public class NetworkModule {
    public static final int CONNECT_TIMEOUT_IN_MS = 30000;


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiImpl provideApiImpl(Retrofit retrofit) {
        return new ApiImpl(retrofit);
    }
}
