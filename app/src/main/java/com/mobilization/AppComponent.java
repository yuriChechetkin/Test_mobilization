package com.mobilization;

import com.mobilization.favorites.favorite.FavoriteComponent;
import com.mobilization.favorites.favorite.FavoriteModule;
import com.mobilization.favorites.history.HistoryComponent;
import com.mobilization.favorites.history.HistoryModule;
import com.mobilization.languages.SelectLanguageComponent;
import com.mobilization.languages.SelectLanguageModule;
import com.mobilization.splash.SplashComponent;
import com.mobilization.splash.SplashModule;
import com.mobilization.storeModule.StoreModule;
import com.mobilization.main.MainComponent;
import com.mobilization.main.MainModule;
import com.mobilization.networkModule.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        StoreModule.class})
public interface AppComponent {
    MainComponent plus(MainModule mainModule);

    HistoryComponent plus(HistoryModule historyModule);

    FavoriteComponent plus(FavoriteModule favoriteModule);

    SplashComponent plus(SplashModule splashModule);

    SelectLanguageComponent plus(SelectLanguageModule slModule);
}
