package com.mobilization;

import android.app.Application;
import android.os.StrictMode;

import com.mobilization.favorites.favorite.FavoriteComponent;
import com.mobilization.favorites.favorite.FavoriteModule;
import com.mobilization.favorites.history.HistoryComponent;
import com.mobilization.favorites.history.HistoryModule;
import com.mobilization.storeModule.StoreModule;
import com.mobilization.main.MainComponent;
import com.mobilization.main.MainModule;
import com.mobilization.networkModule.NetworkModule;

/**
 * Created by mac on 02.04.17.
 */

public class BaseApplication extends Application {

    private AppComponent appComponent;
    private MainComponent mainComponent;
    private HistoryComponent historyComponent;
    private FavoriteComponent favoriteComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .storeModule(new StoreModule())
                .build();
    }


    public MainComponent createMainComponent() {
        mainComponent = appComponent.plus(new MainModule());
        return mainComponent;
    }

    public void releaseMainComponent() {
        mainComponent = null;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }


    public HistoryComponent createHistoryComponent() {
        historyComponent = appComponent.plus(new HistoryModule());
        return historyComponent;
    }

    public void releaseHistoryComponent() {
        historyComponent = null;
    }

    public HistoryComponent getHistoryComponent() {
        return historyComponent;
    }


    public FavoriteComponent createFavoriteComponent() {
        favoriteComponent = appComponent.plus(new FavoriteModule());
        return favoriteComponent;
    }

    public void releaseFavoriteComponent() {
        favoriteComponent = null;
    }

    public FavoriteComponent getFavoriteComponent() {
        return favoriteComponent;
    }

}
